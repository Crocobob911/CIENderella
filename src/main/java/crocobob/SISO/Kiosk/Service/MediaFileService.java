package crocobob.SISO.Kiosk.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaFileService {

    public ResponseEntity<Resource> getFile(String fileName) {
        try {
            String fileDirPath = "src/main/resources/file/";
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
}
