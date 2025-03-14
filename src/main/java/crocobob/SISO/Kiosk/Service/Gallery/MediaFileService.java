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
import java.util.List;

@Service
public class MediaFileService {

    private final String fileDirPath;
    private final MediaInfoService infoService;

    public MediaFileService(MediaInfoService infoService) {
        this.infoService = infoService;

        fileDirPath = readMediaFilePath();
    }

    public ResponseEntity<Resource> getFile(long id){
        MediaInfo mediaToFind = infoService.getMediaInfo(id);
        Path filePath = Paths.get(fileDirPath).resolve(mediaToFind.getFileName()).normalize();

        Resource resource = getFileResource(filePath);
        if(resource.exists()){
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(mediaToFind.getMediaType()))
                    .body(resource);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private Resource getFileResource(Path filePath) {
        try{
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new NoFileNameInLocalException("Invalid file path OR Invalid Name of file. : " + filePath);
        }
    }

    public ResponseEntity<List<Resource>> getAllFile() {
        return null;
    }

//    private List<String> getAllFileNames(){
//        var fileNameList = infoService.getAllValidFileNames(); // file name을 모두 뱉어낼거야. 이걸 토대로 파일들을 로컬에서 읽어와야해!
//        List<Strin>
//    }

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
