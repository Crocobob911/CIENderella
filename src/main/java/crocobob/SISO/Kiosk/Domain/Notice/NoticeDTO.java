package crocobob.SISO.Kiosk.Domain.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDTO {
    String notice;

    public NoticeDTO(String notice) {
        this.notice = notice;
    }
}
