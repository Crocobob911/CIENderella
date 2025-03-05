package crocobob.CIENderella.service.Meal;

import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.repository.Meal.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealReadService {
    private MealRepository repo;

    public MealReadService(MealRepository repo) {
        this.repo = repo;
    }

    public List<MealInfo_AfterProcess> findAllMeals(){
        return repo.findAll();
    }
}
