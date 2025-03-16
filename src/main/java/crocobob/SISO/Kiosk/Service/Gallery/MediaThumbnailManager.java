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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

@Component
public class MediaThumbnailManager {
    private static final String EXTENSION = "png";
    private static Logger log = LoggerFactory.getLogger(MediaThumbnailManager.class);

    public void generateMediaThumbnail(String dirPath, String fileName) {
        File file = new File(dirPath + fileName);
        File thumbNailFile = new File(formatAsThumbnail(dirPath, fileName));
        try{
            FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));

            frameGrab.seekToSecondPrecise(0);
            Picture picture = frameGrab.getNativeFrame();

            BufferedImage bi = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bi, EXTENSION, thumbNailFile);
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
        return dirPath + "thumbnails" + File.separator + "th_" + removeExtension(fileName) + "." + EXTENSION;
    }

    private String removeExtension(String fileName) {
        if(fileName == null || fileName.lastIndexOf(".") == -1){
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
