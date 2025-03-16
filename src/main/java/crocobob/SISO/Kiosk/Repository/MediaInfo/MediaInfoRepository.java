package crocobob.SISO.Kiosk.Repository.MediaInfo;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;

import java.util.List;
import java.util.Optional;

public interface MediaInfoRepository {
    MediaInfo save(MediaInfo mediaInfo);
    List<MediaInfo> findAll();
    List<MediaInfo> findAllByOrderByOrderNumAsc();
    Optional<MediaInfo> findById(Long id);
    Optional<MediaInfo> findByFileName(String fileName);
    Optional<MediaInfo> findByOrderNum(Integer orderNum);
    Optional<MediaInfo> findTopByOrderByOrderNumDesc();
    void delete(MediaInfo mediaInfo);
}
