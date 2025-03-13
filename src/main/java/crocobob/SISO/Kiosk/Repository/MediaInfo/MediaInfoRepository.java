package crocobob.SISO.Kiosk.Repository.MediaInfo;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;

import java.util.List;
import java.util.Optional;

public interface MediaInfoRepository {
    MediaInfo save(MediaInfo mediaInfo);
    List<MediaInfo> findAllByOrderByOrderNumAsc();
    Optional<MediaInfo> findById(Long id);
    Optional<MediaInfo> findByFileName(String fileName);
    Optional<MediaInfo> findByOrderNum(Long orderNum);
    void delete(MediaInfo mediaInfo);
}
