package crocobob.SISO.Cienderella.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReasonDTO {
    String text;

    public ReasonDTO(String text) {
        this.text = text;
    }
}
