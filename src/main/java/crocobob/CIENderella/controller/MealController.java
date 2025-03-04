package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.meal.Meal;
import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.domain.meal.MealRoot;
import crocobob.CIENderella.service.MealService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealController {

    public MealController(MealService service) {
        this.service = service;
    }

    private MealService service;


    @GetMapping(path="/meals/week")
    public List<MealInfo_AfterProcess> findAllWeeklyMeals(){
        return service.findAllMeals();
    }

    @GetMapping(path="/meals/now")
    public List<Meal> getMeals() {
        return null; //
    }

    @GetMapping(path="/meals/parse")
    public String parseWeeklyMealData(){
        return service.createWeeklyMealData();
    }
}
