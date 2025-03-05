package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.service.Meal.MealOutputService;
import crocobob.CIENderella.service.Meal.MealParseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealController {

    public MealController(MealParseService saveService, MealOutputService readService) {
        this.saveService = saveService;
        this.readService = readService;
    }

    private MealParseService saveService;
    private MealOutputService readService;

    @GetMapping(path="/meals")
    public List<MealInfo_AfterProcess> getMealOfNow() {
        return readService.findThreeMealFromNow();
    }

    @GetMapping(path="/meals/{time}")
    public List<MealInfo_AfterProcess> getMealOfGivenTime(@RequestParam int time) {
        return readService.findThreeMealFromNow(time);
    }

    @GetMapping(path="/meals/week")
    public List<MealInfo_AfterProcess> findAllWeeklyMeals(){
        return readService.findAllMeals();
    }

    @GetMapping(path="/meals/parse")
    public String parseWeeklyMealData(){
        return saveService.createWeeklyMealData();
    }
}
