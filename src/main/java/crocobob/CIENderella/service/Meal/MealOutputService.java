package crocobob.CIENderella.service.Meal;

import crocobob.CIENderella.Exception.InvalidMealParameterException;
import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.repository.Meal.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealOutputService {
    private MealRepository repo;

    public MealOutputService(MealRepository repo) {
        this.repo = repo;
    }

    public List<MealInfo_AfterProcess> findAllMeals(){
        return repo.findAll();
    }

    public List<MealInfo_AfterProcess> findMeals(String day, String mealType){
        return repo.findByDateAndMealType(
                getFormattedDateFromString(day),
                convertMealTypeToKorean(mealType));
    }

    private String getFormattedDateFromString(String day){
        if (day.equals("today")) return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        else if (day.equals("tomorrow")) return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        else return "";
    }

    private String convertMealTypeToKorean(String mealType) {
        return switch (mealType) {
            case "morning" -> "아침";
            case "lunch" -> "점심";
            case "dinner" -> "저녁";
            default -> "";
        };
    }

    /*
    public List<MealInfo_AfterProcess> findThreeMealFromNow(){
        return findThreeMealFromNow(LocalDateTime.now().getHour());
    }

    public List<MealInfo_AfterProcess> findThreeMealFromNow(int nowHour){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        String todayDate = today.format(formatter);
        String tomorrowDate = today.plusDays(1).format(formatter);

        List<MealInfo_AfterProcess> returnMealList = new ArrayList<>();
        if(nowHour < 9){ //today M, L, D
            returnMealList = repo.findByDateAndMealType(todayDate, "아침");
            returnMealList.addAll(repo.findByDateAndMealType(todayDate, "점심"));
            returnMealList.addAll(repo.findByDateAndMealType(todayDate, "저녁"));
        }
        else if(nowHour < 14){ // today L, D, tomorrow M
            returnMealList = repo.findByDateAndMealType(todayDate, "점심");
            returnMealList.addAll(repo.findByDateAndMealType(todayDate, "저녁"));
            returnMealList.addAll(repo.findByDateAndMealType(tomorrowDate, "아침"));
        }
        else if(nowHour < 19){ // today D, tomrrow M, L
            returnMealList = repo.findByDateAndMealType(todayDate, "저녁");
            returnMealList.addAll(repo.findByDateAndMealType(tomorrowDate, "아침"));
            returnMealList.addAll(repo.findByDateAndMealType(tomorrowDate, "점심"));
        }else { // tomorrow M, L, D
            returnMealList = repo.findByDateAndMealType(tomorrowDate, "아침");
            returnMealList.addAll(repo.findByDateAndMealType(tomorrowDate, "점심"));
            returnMealList.addAll(repo.findByDateAndMealType(tomorrowDate, "저녁"));
        }

        return returnMealList;
    }
*/
}
