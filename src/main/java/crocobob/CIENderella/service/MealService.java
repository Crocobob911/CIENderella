package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.repository.Meal.MealRepository;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MealService {

    private MealRepository repo;

    public MealService(MealRepository mealRepo) {
        this.repo = mealRepo;
    }

    public List<MealInfo_AfterProcess> findAllMeals(){
        return repo.findAll();
    }

    public String createWeeklyMealData(){
        DeleteAllMealsInDatabase();
        return createMealInfos(readMealJsonFile("src/main/resources/mealJsonExample.json"));
    }

    private void DeleteAllMealsInDatabase(){
        repo.deleteAll();
    }

    private String readMealJsonFile(String path){
        try{
            return Files.readString(Paths.get(path));
        }catch (Exception e){
            e.printStackTrace(); // 이것도 500을 반환하게 해보자.
            return null;
        }
    }

    private String createMealInfos(String jsonString) {
        if(jsonString == null){
            return "Failed to read the Json File. Please Check the File Path.";
        }

        var resultNode = getJsonNode(jsonString, "results");
        for (JsonNode result : resultNode) {
            var cafeteriaNode = result.get("cafeterias");
            for (JsonNode cafeteria : cafeteriaNode) {
                var mealNode = cafeteria.get("meals");
                for (JsonNode meal : mealNode) {
                    var menuNode = meal.get("menus");
                    for (JsonNode menu : menuNode) {
                        try {
                            JSONObject menuObject = new JSONObject(menu.toString());
                            JSONArray menuList = menuObject.getJSONArray("menu");
                            StringBuilder menuString = new StringBuilder(menuList.get(0).toString());
                            for(int i = 1; i < menuList.length(); i++){
                                menuString.append(",").append(menuList.get(i).toString());
                            }
                            MealInfo_AfterProcess mealInfo = new MealInfo_AfterProcess(
                                    result.get("date").asText(),
                                    reduceCafeteriaName(cafeteria.get("cafeteriaName").asText()),
                                    changeMealTypeString(meal.get("mealType").toString()),
                                    menuString.toString()
                            );
                            repo.save(mealInfo);
                        }catch (JSONException e){
                            return "Failed to Update Meals.";
                            // 500으로 보내면 좋을텐데.
                        }
                    }
                }
            }
        }
        return "Server : Update Meals Successfully.";
    }


    private JsonNode getJsonNode(String jsonString, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readTree(jsonString).get(key);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    private String reduceCafeteriaName(String name){
        return switch (name) {
            case "생활관식당(블루미르308관)" -> "310관 B4층";
            case "생활관식당(블루미르309관)" -> "309관";
            case "참슬기식당(310관 B4층)" -> "308관";
            case "학생식당(303관B1층)" -> "303관 B1층";
            case "University Club(102관11층)" -> "102관 12층";
            default -> name;
        };
    }

    private String changeMealTypeString(String mealType){
        return switch (mealType){
            case "\\\"아침\\\"" -> "아침";
            case "\\\"점심\\\"" -> "점심";
            case "\\\"저녁\\\"" -> "저녁";
            default -> mealType;
        };
    }
}
