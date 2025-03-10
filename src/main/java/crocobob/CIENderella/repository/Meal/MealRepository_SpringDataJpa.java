package crocobob.CIENderella.repository.Meal;

import crocobob.CIENderella.domain.Kiosk.MealInfo_AfterProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository_SpringDataJpa extends JpaRepository<MealInfo_AfterProcess, Long>, MealRepository {
}
