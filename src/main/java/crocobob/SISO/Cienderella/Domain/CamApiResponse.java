package crocobob.SISO.Cienderella.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CamApiResponse {
    private int peopleCount;
    private Boolean isPeopleThere;
}
