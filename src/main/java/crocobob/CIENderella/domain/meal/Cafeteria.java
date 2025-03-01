package crocobob.CIENderella.domain.meal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cafeteria {
    private String cafeteriaName;
    private List<Meal> meals;
}
