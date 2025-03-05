package crocobob.CIENderella.domain.meal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MealCrawlResult {
    private List<MealRoot> results;
}
