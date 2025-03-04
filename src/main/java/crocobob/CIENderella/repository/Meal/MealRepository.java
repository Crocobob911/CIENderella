package crocobob.CIENderella.repository.Meal;

import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;

import java.util.List;
import java.util.Optional;

public interface MealRepository {
    MealInfo_AfterProcess save(MealInfo_AfterProcess meal);
    List<MealInfo_AfterProcess> findAll();
    Optional<MealInfo_AfterProcess> findByDateAndMealType(String date, String mealType);
    void delete(MealInfo_AfterProcess meal);
    void deleteAll();
}
