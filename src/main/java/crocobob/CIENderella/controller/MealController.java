package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.meal.Meal;
import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.service.Meal.MealReadService;
import crocobob.CIENderella.service.Meal.MealSaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealController {

    public MealController(MealSaveService saveService, MealReadService readService) {
        this.saveService = saveService;
        this.readService = readService;
    }

    private MealSaveService saveService;
    private MealReadService readService;


    @GetMapping(path="/meals/week")
    public List<MealInfo_AfterProcess> findAllWeeklyMeals(){
        return readService.findAllMeals();
    }

    @GetMapping(path="/meals/now")
    public List<Meal> getMeals() {
        return null; //
    }

    @GetMapping(path="/meals/parse")
    public String parseWeeklyMealData(){
        return saveService.createWeeklyMealData();
    }
}
