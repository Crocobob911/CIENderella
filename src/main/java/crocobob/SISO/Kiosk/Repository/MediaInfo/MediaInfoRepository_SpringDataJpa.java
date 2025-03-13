package crocobob.SISO.Kiosk.Repository.MediaInfo;

import crocobob.SISO.Kiosk.Domain.Gallery.MediaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaInfoRepository_SpringDataJpa extends JpaRepository<MediaInfo, Long>, MediaInfoRepository {
}
