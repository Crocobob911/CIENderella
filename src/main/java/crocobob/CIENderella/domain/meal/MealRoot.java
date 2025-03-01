package crocobob.CIENderella.domain.meal;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MealRoot {
    private String date; // 이거 형식 좀 쎄한데
    private List<Cafeteria> cafeterias;
}
