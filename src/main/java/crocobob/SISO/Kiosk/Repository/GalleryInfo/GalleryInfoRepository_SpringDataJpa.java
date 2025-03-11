package crocobob.SISO.Kiosk.Repository.GalleryInfo;

import crocobob.SISO.Kiosk.Domain.Gallery.GalleryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryInfoRepository_SpringDataJpa extends JpaRepository<GalleryInfo, Long>, GalleryInfoRepository {
}
