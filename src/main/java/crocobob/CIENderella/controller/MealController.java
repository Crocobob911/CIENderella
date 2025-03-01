package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.meal.Meal;
import crocobob.CIENderella.domain.meal.MealRoot;
import crocobob.CIENderella.service.MealService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealController {

    public MealController(MealService service) {
        this.service = service;
    }

    private MealService service;

    @PostMapping("/meal")
    public String addMeal(@RequestBody MealRoot mealRoot) {
        return service.addMeal(mealRoot);
    }
}
