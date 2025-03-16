package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import crocobob.SISO.Kiosk.Repository.MediaInfo.MediaInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MediaInfoService {
    private final MediaInfoRepository repo;

    public MediaInfoService(MediaInfoRepository mediaInfoRepository) {
        this.repo = mediaInfoRepository;
    }

    public MediaInfo processFile(MultipartFile file, String fileName){
        MediaInfo mediaInfo = new MediaInfo(
                createOrderNum(),
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

    public Boolean IsFileNameDuplicate(String fileName){
        return repo.findByFileName(fileName).isPresent();
    }

    private int createOrderNum() {
        var maxOrderNumInfo = repo.findTopByOrderByOrderNumDesc();
        return maxOrderNumInfo.map(mediaInfo -> mediaInfo.getOrderNum() + 1).orElse(1);
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
        deleteInvalidMediaInfo();
        return repo.findAllByOrderByOrderNumAsc();
    }

    public void deleteMediaInfo(Long id) {
        repo.delete(repo.findById(id).isPresent() ? repo.findById(id).get() : null);
    }

    public MediaInfo extendDueDate(Long id) {
        var mediaInfo = getMediaInfo(id);
        mediaInfo.setDueDateTime(mediaInfo.getDueDateTime().plusDays(7));
        return repo.save(mediaInfo);
    }

    public MediaInfo changeOrderNum(Long id, int upDown) {
        deleteInvalidMediaInfo();

        var mediaInfoA = getMediaInfo(id);
        var mediaInfoB = repo.findByOrderNum(mediaInfoA.getOrderNum() + upDown)
                .orElse(null);

        if(mediaInfoB == null){
            return mediaInfoA;
        }

        var tempOrderNum = mediaInfoA.getOrderNum();
        mediaInfoA.setOrderNum(mediaInfoB.getOrderNum());
        mediaInfoB.setOrderNum(tempOrderNum);

        repo.save(mediaInfoB);
        return repo.save(mediaInfoA);
    }

    private void deleteInvalidMediaInfo() {
        var infoList = repo.findAll();
        for(var mediaInfo : infoList){
            if(mediaInfo.getDueDateTime().isBefore(LocalDateTime.now())){
                repo.delete(mediaInfo);
            }
        }
    }
}
