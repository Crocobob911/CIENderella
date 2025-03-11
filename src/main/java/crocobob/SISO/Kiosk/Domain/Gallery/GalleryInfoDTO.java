package crocobob.SISO.Kiosk.Domain.Gallery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GalleryInfoDTO {
    Integer photoDisplayTime;
    Integer videoMaxDisplayTime;
    Integer discordLikeThreshold;
}
