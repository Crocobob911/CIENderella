package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.NoFileNameInLocalException;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class MediaThumbnailManager {
    private static final String EXTENSION = "png";
    private static Logger log = LoggerFactory.getLogger(MediaThumbnailManager.class);

    public void generateMediaThumbnail(File file) {
        String fileExtension = getFileExtension(file.getName());
        String thumbnailPath = file.getAbsolutePath().replace("."+ fileExtension, ".png");

        log.info("file.getParent : " + file.getParent());
        log.info("file.getName : " + file.getName());
        log.info("thumbnailPath : " + new File(thumbnailPath).getName());

        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm", "-v", file.getParent() + ":/data", // 디렉토리 마운트
                "jrottenberg/ffmpeg", "-i", "/data/" + file.getName(), //
                "-ss", "00:00:01.000", "-vframes", "1", // 영상의 1초 부분에, 1프레임을 추출
                "-f", "image2", "-c:v", "png",
                "/data/thumbnails/" + new File(thumbnailPath).getName()); // thumbnails 폴더에 .png 저장
        pb.redirectErrorStream(true);
        try{
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public Resource getThumbnail(String dirPath, String fileName) {
        String filePath = formatAsThumbnail(dirPath, fileName);
        try{
            return new UrlResource(filePath);
        } catch (MalformedURLException e) {
            throw new NoFileNameInLocalException("Invalid file path OR Invalid Name of file. : " + filePath);
        }
    }

    private String formatAsThumbnail(String dirPath, String fileName) {
        return dirPath + "thumbnails" + File.separator + removeExtension(fileName) + "." + EXTENSION;
    }

    private String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filePath.substring(dotIndex + 1);
    }

    private String removeExtension(String fileName) {
        if(fileName == null || fileName.lastIndexOf(".") == -1){
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
