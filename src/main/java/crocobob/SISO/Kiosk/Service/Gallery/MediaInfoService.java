package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Repository.MediaInfo.MediaInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Service
public class MediaInfoService {
    private final MediaInfoRepository repo;

    public MediaInfoService(MediaInfoRepository mediaInfoRepository) {
        this.repo = mediaInfoRepository;
    }

    public MediaInfo processFile(MultipartFile file){
        MediaInfo mediaInfo = new MediaInfo(
                calculateOrderNum(),
                file.getOriginalFilename(),
                file.getContentType(),
                "default-uploader",
                convertBytesToMB(file.getSize()),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7)
        );

        repo.save(mediaInfo);
        return mediaInfo;
    }

    public Boolean IsFileNameDuplicate(String fileName){
        return repo.findByFileName(fileName).isPresent();
    }

    private long calculateOrderNum() {
        return 1; // 일단 1을 뱉어.
    }

    private double convertBytesToMB(long bytes) {
        double mbSize = (double) bytes / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(mbSize));
    }
}
