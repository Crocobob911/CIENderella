package crocobob.SISO.Kiosk.Domain.Gallery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    Long orderNum;
    String fileName;
    String mediaType;
    String uploader;
    Double fileSizeMB;
    LocalDateTime uploadDateTime;
    LocalDateTime dueDateTime;

    public MediaInfo(Long orderNum, String fileName, String mediaType, String uploader, Double fileSizeMB, LocalDateTime uploadDateTime, LocalDateTime dueDateTime) {
        this.orderNum = orderNum;
        this.fileName = fileName;
        this.mediaType = mediaType;
        this.uploader = uploader;
        this.fileSizeMB = fileSizeMB;
        this.uploadDateTime = uploadDateTime;
        this.dueDateTime = dueDateTime;
    }
}
