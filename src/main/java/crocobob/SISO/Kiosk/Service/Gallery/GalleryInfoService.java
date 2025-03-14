package crocobob.SISO.Kiosk.Service.Gallery;

import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Kiosk.Domain.Gallery.GalleryInfo;
import crocobob.SISO.Kiosk.Domain.Gallery.GalleryInfoDTO;
import crocobob.SISO.Kiosk.Repository.GalleryInfo.GalleryInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class GalleryInfoService {
    private final GalleryInfoRepository repo;

    public GalleryInfoService(GalleryInfoRepository repository) {
        this.repo = repository;
    }

    public GalleryInfoDTO getGalleryInfo() {
        var info = getGalleryInfoFromRepo();
        GalleryInfoDTO dto = new GalleryInfoDTO(
                info.getPhotoDisplayTime(),
                info.getVideoMaxDisplayTime(),
                info.getDiscordLikeThreshold()
        );
        return dto;
    }

    public GalleryInfo patchUpdateGalleryInfo(GalleryInfoDTO newDto) {
        GalleryInfo info = getGalleryInfoFromRepo();

        if(newDto.getPhotoDisplayTime() != null) info.setPhotoDisplayTime(newDto.getPhotoDisplayTime());
        if(newDto.getVideoMaxDisplayTime() != null) info.setVideoMaxDisplayTime(newDto.getVideoMaxDisplayTime());
        if(newDto.getDiscordLikeThreshold() != null) info.setDiscordLikeThreshold(newDto.getDiscordLikeThreshold());

        return repo.save(info);
    }

    private GalleryInfo getGalleryInfoFromRepo(){
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(() -> new DBEntityNotFoundException("GalleryInfo not found"));
    }
}
