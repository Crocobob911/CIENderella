package crocobob.SISO.Cienderella.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
