package crocobob.SISO.Kiosk.Repository.Meal;

import crocobob.SISO.Kiosk.Domain.MealInfo_AfterProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository_SpringDataJpa extends JpaRepository<MealInfo_AfterProcess, Long>, MealRepository {
}
