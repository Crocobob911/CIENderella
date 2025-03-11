package crocobob.SISO.Cienderella.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Form {
    String time;
    Boolean status;
    String title;
    String writer;
    String password;
    String content;
}