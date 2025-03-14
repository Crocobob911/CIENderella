package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.NoFileNameInLocalException;
import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaFileService {

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
            fileName = "_" + fileName;
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
        File uploadDir = new File(fileDirPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        File destination = new File(fileDirPath + fileName);
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

}
