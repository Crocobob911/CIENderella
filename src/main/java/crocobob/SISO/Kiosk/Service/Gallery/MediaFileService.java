package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.NoFileNameInLocalException;
import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaFileService {
    private static Logger logger = LoggerFactory.getLogger(MediaFileService.class);

    private final String fileDirPath;
    private final MediaInfoService infoService;

    public MediaFileService(MediaInfoService infoService) {
        this.infoService = infoService;

        fileDirPath = readMediaFilePath();
    }

    public ResponseEntity<Resource> getResponseEntityWithResource(long id){
        return makeResponseEntity(infoService.getMediaInfo(id));
    }

    public ResponseEntity<Resource> getResponseEntityWithResource(String fileName){
        return makeResponseEntity(infoService.getMediaInfo(fileName));
    }

    private ResponseEntity<Resource> makeResponseEntity(MediaInfo mediaInfo) {
        Resource resource = getResource(mediaInfo.getFileName());
        if(resource.exists()){
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(mediaInfo.getMediaType()))
                    .body(resource);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private Resource getResource(String fileName) {
        Path filePath = Paths.get(fileDirPath).resolve(fileName).normalize();
        return getFileFromStorage(filePath);
    }

    private Resource getFileFromStorage(Path filePath) {
        try{
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new NoFileNameInLocalException("Invalid file path OR Invalid Name of file. : " + filePath);
        }
    }

    public MediaInfo processFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        while(infoService.IsFileNameDuplicate(fileName)) {
            fileName = fileName + "1";
        }

        var mediaInfoOfFile = infoService.processFile(file, fileName);

        try {
            saveFileInLocalDirectory(file, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mediaInfoOfFile;
    }

    private void saveFileInLocalDirectory(MultipartFile file, String fileName) throws IOException {
        String cleanedFileName = fileName.replaceAll("[\\r\\n]","").replaceAll("[^a-zA-Z0-9.\\-]","");
        String encodeFileName = URLEncoder.encode(cleanedFileName, StandardCharsets.UTF_8);

        File uploadDir = new File(fileDirPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        logger.info("Saving file " + encodeFileName + " in " + uploadDir.getAbsolutePath());

        Path destination = Paths.get(uploadDir.getAbsolutePath()).resolve(encodeFileName).normalize();
        file.transferTo(destination);
    }

    private String readMediaFilePath(){
        try{
            ClassPathResource resource = new ClassPathResource("mediaFilePath.txt");
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteFile(Long id){
        MediaInfo info = infoService.getMediaInfo(id);
        infoService.deleteMediaInfo(id);
        File file = new File(fileDirPath + info.getFileName());
        return file.delete();
    }
}
