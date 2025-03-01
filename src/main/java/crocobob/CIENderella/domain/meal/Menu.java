package crocobob.CIENderella.domain.meal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Menu {
    private String menuType;
    private String price;
    private String startTime;
    private String endTime;
    private List<String> menu;
    private String calories;
}
