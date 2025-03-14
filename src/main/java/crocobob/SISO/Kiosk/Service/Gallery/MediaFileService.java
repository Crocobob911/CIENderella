package crocobob.SISO.Kiosk.Service.Gallery;

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

@Service
public class MediaFileService {

    private final String fileDirPath;
    private final MediaInfoService infoService;

    public MediaFileService(MediaInfoService infoService) {
        this.infoService = infoService;

        fileDirPath = "/home/crocobob/CIENderella_Media/";
    }

    public ResponseEntity<Resource> getFile(String fileName) {
        try {
            Path filePath = Paths.get(fileDirPath).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(getMediaType(filePath))
                        .body(resource);
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private MediaType getMediaType(Path fileName) {
        try{
            return MediaType.parseMediaType(
                    Files.probeContentType(
                            Paths.get(fileName.toUri())
                    )
            );
        }catch (IOException e){
            throw new IllegalArgumentException();
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
