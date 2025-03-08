package crocobob.CIENderella.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Content {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    Integer startTime;
    Integer endTime;
    String password;
    Boolean status;
    String title;
    String text;

    public Content(int startTime, int endTime, String password, Boolean status, String title, String text) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.password = password;
        this.status = status;
        this.title = title;
        this.text = text;
    }
}
