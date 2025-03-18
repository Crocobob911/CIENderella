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
    private final MediaThumbnailManager thumbnailManager;

    public MediaFileService(MediaInfoService infoService, MediaThumbnailManager thumbnailManager) {
        this.infoService = infoService;
        this.thumbnailManager = thumbnailManager;

        fileDirPath = readMediaFilePath();
    }

    // ------------------- <<READ>> -------------------

    public Resource getResource(String fileName) {
        Path filePath = Paths.get(fileDirPath).resolve(fileName).normalize();
        return getFileFromStorage(filePath);
    }

    public Resource getThumbnail(Long id) {
        var info = infoService.getMediaInfo(id);

        if (!info.getMediaType().startsWith("video"))
            throw new NoThumbnailCreatedException("There is no Thumbnail, because the media IS NOT VIDEO.");

        String fileNameNoExtension = removeExtension(info.getFileName());
        Path filePath = Paths.get(fileDirPath + "thumbnails/" + fileNameNoExtension + ".png");

        return getFileFromStorage(filePath);
    }

    private Resource getFileFromStorage(Path filePath) {
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

        try {
            saveFileInLocalDirectory(file, fileName);
            makeThumbnail(mediaInfoOfFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mediaInfoOfFile;
    }

    public MediaInfo processFile(String urlString, String uploader) {
        String fileName = makeFileNameDoesntDuplicate(getFileNameFromUrl(urlString));

        try{
            Path filePath = downloadFileFromUrl(urlString, fileName);
            return infoService.processFile(filePath, fileName, uploader);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private String getFileNameFromUrl(String fileUrl) {
        return Paths.get(URI.create(fileUrl).getPath()).getFileName().toString();
    }

    private Path downloadFileFromUrl(String urlString, String fileName) throws MalformedURLException {
        Path filePath = Paths.get(fileDirPath).resolve(fileName);

        URL url = new URL(urlString);
        try (InputStream in = url.openStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
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

    private void saveFileInLocalDirectory(MultipartFile file, String fileName) throws IOException {
        File uploadDir = new File(fileDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        logger.info("Saving file {} in {}", fileName, uploadDir.getAbsolutePath());

        Path destination = uploadDir.toPath().resolve(fileName).normalize();
        file.transferTo(destination);
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
        thumbnailManager.generateMediaThumbnail(file);
    }

    private String removeExtension(String fileName) {
        if(fileName == null || fileName.lastIndexOf(".") == -1){
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    // ------------------- <<DELETE>> -------------------

    public String deleteFile(Long id) {
        MediaInfo info = infoService.getMediaInfo(id);
        infoService.deleteMediaInfoById(id);
        File file = new File(fileDirPath + info.getFileName());
        try {
            file.delete();
            return "Successfully deleted file.";
        } catch (Exception e) {
            return "Error deleting file.";
        }
    }

}