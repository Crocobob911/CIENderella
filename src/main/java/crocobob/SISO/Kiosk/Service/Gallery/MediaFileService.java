package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.NoFileNameInLocalException;
import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaFileService {
    private static Logger logger = LoggerFactory.getLogger(MediaFileService.class);

    private final String fileDirPath;
    private final MediaInfoService infoService;
    private final MediaThumbnailManager thumbnailManager;

    public MediaFileService(MediaInfoService infoService, MediaThumbnailManager thumbnailManager) {
        this.infoService = infoService;
        this.thumbnailManager = thumbnailManager;

        fileDirPath = readMediaFilePath();
    }

    public Resource getResource(String fileName) {
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
        String fileName = makeFileNameDoesntDuplicate(file.getOriginalFilename());
        var mediaInfoOfFile = infoService.processFile(file, fileName);

        try {
            saveFileInLocalDirectory(file, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mediaInfoOfFile;
    }

    private String makeFileNameDoesntDuplicate(String fileName) {
        while(infoService.IsFileNameDuplicate(fileName)) {
            fileName = "_" + fileName;
        }
        return fileName;
    }

    private void saveFileInLocalDirectory(MultipartFile file, String fileName) throws IOException {
        File uploadDir = new File(fileDirPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        logger.info("Saving file " + fileName + " in " + uploadDir.getAbsolutePath());

        Path destination = uploadDir.toPath().resolve(fileName).normalize();
        file.transferTo(destination);

        makeThumbnail(fileName);
    }

    private String readMediaFilePath(){
        ClassPathResource resource = new ClassPathResource("mediaFilePath.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            return reader.readLine().trim();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public String deleteFile(Long id){
        MediaInfo info = infoService.getMediaInfo(id);
        infoService.deleteMediaInfoById(id);
        File file = new File(fileDirPath + info.getFileName());
        try{
            file.delete();
            return "Successfully deleted file.";
        }catch (Exception e){
            return "Error deleting file.";
        }
    }

    private void makeThumbnail(String fileName) {
        thumbnailManager.generateMediaThumbnail(fileDirPath, fileName);
    }

    public Resource getThumbnail(Long id) {
        var mediaInfo = infoService.getMediaInfo(id);
        return thumbnailManager.getThumbnail(fileDirPath, mediaInfo.getFileName());
    }
}
