package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Repository.MediaInfo.MediaInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MediaInfoService {
    private final MediaInfoRepository repo;

    public MediaInfoService(MediaInfoRepository mediaInfoRepository) {
        this.repo = mediaInfoRepository;
    }

    public MediaInfo processFile(MultipartFile file, String fileName){
        MediaInfo mediaInfo = new MediaInfo(
                calculateOrderNum(),
                fileName,
                file.getContentType(),
                "default-uploader",
                convertBytesToMB(file.getSize()),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7)
        );

        repo.save(mediaInfo);
        return mediaInfo;
    }

    public MediaInfo getMediaInfo(long id) {
        return repo.findById(id).orElseThrow(()-> new DBEntityNotFoundException("No media with id : " + id));
    }

    public MediaInfo getMediaInfo(String fileName) {
        return repo.findByFileName(fileName).orElseThrow(()-> new DBEntityNotFoundException("No media with fileName : " + fileName));
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

    public List<MediaInfo> getAllMediaInfo(){
        return repo.findAll();
    }

    public List<MediaInfo> getAllValidMediaInfo() {
        List<MediaInfo> infoList = repo.findAllByOrderByOrderNumAsc();
        infoList.removeIf(info -> info.getDueDateTime().isBefore(LocalDateTime.now()));

        return infoList;
    }

    public void deleteMediaInfo(Long id) {
        repo.delete(repo.findById(id).isPresent() ? repo.findById(id).get() : null);
    }
}
