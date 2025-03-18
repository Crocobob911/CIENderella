package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.NoFileNameInLocalException;
import crocobob.SISO.Exception.NoThumbnailCreatedException;
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
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class MediaFileService {
    private static final Logger logger = LoggerFactory.getLogger(MediaFileService.class);

    private final String fileDirPath;
    private final MediaInfoService infoService;
    private final MediaThumbnailService thumbnailService;

    public MediaFileService(MediaInfoService infoService, MediaThumbnailService thumbnailService) {
        this.infoService = infoService;
        this.thumbnailService = thumbnailService;

        fileDirPath = readMediaFilePath();
    }

    // ------------------- <<READ>> -------------------

    public Resource getResource(String fileName) {
        Path filePath = Paths.get(fileDirPath).resolve(fileName).normalize();
        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new NoFileNameInLocalException("Invalid file path OR Invalid Name of file. : " + filePath);
        }
    }

    // ------------------- <<CREATE>> -------------------

    public MediaInfo processFile(MultipartFile file, String uploader) {
        String fileName = makeFileNameDoesntDuplicate(file.getOriginalFilename());
        var mediaInfoOfFile = infoService.processFile(file, fileName, uploader);
        saveFileInLocalDirectory(file, fileName);
        makeThumbnail(mediaInfoOfFile);
        return mediaInfoOfFile;
    }

    public MediaInfo processFile(String urlString, String uploader) {
        String fileName = makeFileNameDoesntDuplicate(getFileNameFromUrl(urlString));
        Path filePath = saveFileInLocalDirectory_URL(urlString, fileName);
        var mediaInfoOfFile = infoService.processFile(filePath, fileName, uploader);
        makeThumbnail(mediaInfoOfFile);
        return mediaInfoOfFile;
    }

    private String getFileNameFromUrl(String fileUrl) {
        return Paths.get(URI.create(fileUrl).getPath()).getFileName().toString();
    }

    private Path saveFileInLocalDirectory(MultipartFile file, String fileName) {
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        logger.info("Saving file {} in {}", fileName, fileDir.getAbsolutePath());

        Path filePath = fileDir.toPath().resolve(fileName).normalize();
        try{
            file.transferTo(filePath);
        }catch (IOException e){
            throw new RuntimeException();
        }

        return filePath;
    }

    private Path saveFileInLocalDirectory_URL(String urlString, String fileName) {
        Path filePath = Paths.get(fileDirPath).resolve(fileName);
        try{
            URL url = new URL(urlString);
            InputStream in = url.openStream();
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    private String makeFileNameDoesntDuplicate(String fileName) {
        while (infoService.IsFileNameDuplicate(fileName)) {
            fileName = "_" + fileName;
        }
        return fileName;
    }

    private String readMediaFilePath() {
        ClassPathResource resource = new ClassPathResource("mediaFilePath.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()))) {
            return reader.readLine().trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeThumbnail(MediaInfo info) {
        if (!info.getMediaType().startsWith("video")) return;

        File file = new File(fileDirPath + info.getFileName());
        thumbnailService.generateMediaThumbnail(file);
    }

    // ------------------- <<DELETE>> -------------------

    public String deleteFile(Long id) {
        MediaInfo info = infoService.getMediaInfo(id);
        File file = new File(fileDirPath + info.getFileName());
        try {
            file.delete();
        } catch (Exception e) {
            return "Error deleting file.";
        }

        try{
            File thumnailFile = new File(fileDirPath + thumbnailService.getThumbnailFilePath(id));
            thumnailFile.delete();
        }catch (NoThumbnailCreatedException _){

        }

        infoService.deleteMediaInfoById(id);
        return file.getAbsolutePath();
    }
}