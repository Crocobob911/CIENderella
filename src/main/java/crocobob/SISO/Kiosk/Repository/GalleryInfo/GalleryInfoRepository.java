package crocobob.SISO.Kiosk.Repository.GalleryInfo;

import crocobob.SISO.Kiosk.Domain.Gallery.GalleryInfo;

import java.util.Optional;

public interface GalleryInfoRepository {
    GalleryInfo save(GalleryInfo galleryInfo);
    Optional<GalleryInfo> findTopByOrderByIdDesc();
    void delete(GalleryInfo galleryInfo);
}
