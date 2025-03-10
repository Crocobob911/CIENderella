package crocobob.CIENderella.service;

import crocobob.CIENderella.Exception.DBEntityNotFoundException;
import crocobob.CIENderella.domain.GalleryInfo;
import crocobob.CIENderella.domain.GalleryInfoDTO;
import crocobob.CIENderella.domain.cienderella.Content;
import crocobob.CIENderella.repository.GalleryInfo.GalleryInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GalleryInfoService {
    private final GalleryInfoRepository repo;

    public GalleryInfoService(GalleryInfoRepository repository) {
        this.repo = repository;
    }

    public GalleryInfo getGalleryInfo() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(() -> new DBEntityNotFoundException("GalleryInfo not found"));
    }

    public void patchUpdateGalleryInfo(GalleryInfoDTO newDto) {
        GalleryInfo info = getGalleryInfo();

        if(newDto.getPhotoDisplayTime() != null) info.setPhotoDisplayTime(newDto.getPhotoDisplayTime());
        if(newDto.getVideoMaxDisplayTime() != null) info.setVideoMaxDisplayTime(newDto.getVideoMaxDisplayTime());
        if(newDto.getDiscordLikeThreshold() != null) info.setDiscordLikeThreshold(newDto.getDiscordLikeThreshold());

        repo.save(info);
    }
}
