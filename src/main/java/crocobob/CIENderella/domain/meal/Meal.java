package crocobob.CIENderella.domain.meal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Meal {
    private String mealType;
    private boolean shouldShowTime;
    private String startTime;
    private String endTime;
    private List<Menu> menus;
}
