package crocobob.CIENderella.repository.GalleryInfo;

import crocobob.CIENderella.domain.GalleryInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryInfoRepository_SpringDataJpa extends JpaRepository<GalleryInfo, Long>, GalleryInfoRepository {
}
