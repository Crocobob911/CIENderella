package crocobob.CIENderella.repository.GalleryInfo;

import crocobob.CIENderella.domain.GalleryInfo;
import crocobob.CIENderella.domain.GalleryInfoDTO;
import crocobob.CIENderella.domain.cienderella.Content;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface GalleryInfoRepository {
    GalleryInfo save(GalleryInfo galleryInfo);
    Optional<GalleryInfo> findTopByOrderByIdDesc();
    void delete(Content content);
}
