package crocobob.CIENderella.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ContentDTO {
    String password;
    Boolean status;
    String title;
    String text;

    public ContentDTO(String password, Boolean status, String title, String text) {
        this.password = password;
        this.status = status;
        this.title = title;
        this.text = text;
    }
}
