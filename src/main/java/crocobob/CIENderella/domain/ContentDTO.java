package crocobob.CIENderella.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    Integer startTime;
    Integer endTime;
    String password;
    Boolean status;
    String title;
    String text;
}
