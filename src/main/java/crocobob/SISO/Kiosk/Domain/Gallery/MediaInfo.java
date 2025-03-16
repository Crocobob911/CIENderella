package crocobob.SISO.Kiosk.Domain.Gallery;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MediaInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer orderNum;
    String fileName;
    String mediaType;
    String uploader;
    Double fileSizeMB;
    LocalDateTime uploadDateTime;
    LocalDateTime dueDateTime;

    public MediaInfo(Integer orderNum, String fileName, String mediaType, String uploader, Double fileSizeMB, LocalDateTime uploadDateTime, LocalDateTime dueDateTime) {
        this.orderNum = orderNum;
        this.fileName = fileName;
        this.mediaType = mediaType;
        this.uploader = uploader;
        this.fileSizeMB = fileSizeMB;
        this.uploadDateTime = uploadDateTime;
        this.dueDateTime = dueDateTime;
    }
}
