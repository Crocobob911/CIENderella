package crocobob.CIENderella.domain.cienderella;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriterDTO {
    String text;

    public WriterDTO(String text) {
        this.text = text;
    }
}
