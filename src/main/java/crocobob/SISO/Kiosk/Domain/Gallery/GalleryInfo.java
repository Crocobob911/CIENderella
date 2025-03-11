package crocobob.SISO.Kiosk.Domain.Gallery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GalleryInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer photoDisplayTime;
    Integer videoMaxDisplayTime;
    Integer discordLikeThreshold;

    public GalleryInfo(Integer photoDisplayTime, Integer videoMaxDisplayTime, Integer discordLikeThreshold) {
        this.photoDisplayTime = photoDisplayTime;
        this.videoMaxDisplayTime = videoMaxDisplayTime;
        this.discordLikeThreshold = discordLikeThreshold;
    }
}
