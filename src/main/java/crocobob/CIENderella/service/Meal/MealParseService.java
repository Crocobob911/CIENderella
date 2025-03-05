package crocobob.CIENderella.service.Meal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.CIENderella.domain.MealInfo_AfterProcess;
import crocobob.CIENderella.repository.Meal.MealRepository;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealParseService {

    private MealRepository repo;

    public MealParseService(MealRepository mealRepo) {
        this.repo = mealRepo;
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
        for (JsonNode result : resultNode) { // result = date, cafeterias
            var cafeteriaNode = result.get("cafeterias");
            for (JsonNode cafeteria : cafeteriaNode) { // cafeteria = cafeteriaName, meals
                var mealNode = cafeteria.get("meals");
                if(cafeteria.get("cafeteriaName").asText().equals("University Club(102관11층)")){
                    for (JsonNode meal : mealNode) {
                        var menuNode = meal.get("menus");
                        List<String> menuStringList = new ArrayList<>();
                        for (JsonNode menu : menuNode) {
                            JSONObject menuObject = new JSONObject(menu.toString());
                            JSONArray menuList = menuObject.getJSONArray("menu");

                            for (int i = 0; i < menuList.length(); i++) {
                                menuStringList.add(menuList.get(i).toString());
                            }
                        }
                        String menuString = String.join(",", convertListToArray(menuStringList));

                        MealInfo_AfterProcess mealInfo = new MealInfo_AfterProcess(
                                result.get("date").asText(),
                                reduceCafeteriaName(cafeteria.get("cafeteriaName").asText()),
                                changeMealTypeString(meal.get("mealType").toString()),
                                menuString
                        );
                        repo.save(mealInfo);
                    }
                }else{
                    for (JsonNode meal : mealNode) { // meal = mealType, menus
                        var menuNode = meal.get("menus");
                        for (JsonNode menu : menuNode) { // menu = menu(menuStringList)
                            try {
                                JSONObject menuObject = new JSONObject(menu.toString());
                                JSONArray menuList = menuObject.getJSONArray("menu");

                                String[] menuStringList = new String[menuList.length()];
                                for (int i = 0; i < menuList.length(); i++) {
                                    menuStringList[i] = menuList.get(i).toString();
                                }
                                String menuString = String.join(",", menuStringList);

                                if(cafeteria.get("cafeteriaName").asText().equals("학생식당(303관B1층)") && menuString.length() < 15)
                                    continue; // 303관 단품 컷

                                MealInfo_AfterProcess mealInfo = new MealInfo_AfterProcess(
                                        result.get("date").asText(),
                                        reduceCafeteriaName(cafeteria.get("cafeteriaName").asText()),
                                        changeMealTypeString(meal.get("mealType").toString()),
                                        menuString
                                );
                                repo.save(mealInfo);

                            }catch (JSONException e){
                                return "Failed to Update Meals. \n" + e.getMessage();
                                // 500으로 보내면 좋을텐데.
                            }
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
            case "생활관식당(블루미르308관)" -> "308관 기숙사";
            case "생활관식당(블루미르309관)" -> "309관 기숙사";
            case "참슬기식당(310관 B4층)" -> "310관 B4층";
            case "학생식당(303관B1층)" -> "303관 B1층";
            case "University Club(102관11층)" -> "102관 12층";
            default -> name;
        };
    }

    private String changeMealTypeString(String mealType){
        return mealType.replaceAll("[^가-힣]", "");
    }

    private String[] convertListToArray(List<String> list){
        return list.toArray(new String[list.size()]);
    }
}
