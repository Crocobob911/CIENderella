package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.NoThumbnailCreatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MediaThumbnailService {
    private static final String EXTENSION = "png";
    private static Logger log = LoggerFactory.getLogger(MediaThumbnailService.class);

    private final MediaInfoService infoService;

    public MediaThumbnailService(MediaInfoService infoService) {
        this.infoService = infoService;
    }

    public String getThumbnailFilePath(Long id) {
        var info = infoService.getMediaInfo(id);

        if (!info.getMediaType().startsWith("video"))
            throw new NoThumbnailCreatedException("There is no Thumbnail, because the media IS NOT VIDEO.");

        String fileNameNoExtension = removeExtension(info.getFileName());
        return "thumbnails" + File.separator + fileNameNoExtension + ".png";
    }

    public void generateMediaThumbnail(File file) {
        String fileExtension = getFileExtension(file.getName());
        String thumbnailPath = file.getAbsolutePath().replace("."+ fileExtension, ".png");

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
