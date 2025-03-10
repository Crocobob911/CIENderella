package crocobob.CIENderella.repository.Meal;

import crocobob.CIENderella.domain.Kiosk.MealInfo_AfterProcess;

import java.util.List;

public interface MealRepository {
    MealInfo_AfterProcess save(MealInfo_AfterProcess meal);
    List<MealInfo_AfterProcess> findAll();
    List<MealInfo_AfterProcess> findByDateAndMealType(String date, String mealType);
    void delete(MealInfo_AfterProcess meal);
    void deleteAll();
}
