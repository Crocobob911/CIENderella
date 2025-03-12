package crocobob.SISO.Kiosk.Service.Meal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.SISO.Kiosk.Domain.MealInfo_AfterProcess;
import crocobob.SISO.Kiosk.Repository.Meal.MealRepository;
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
        return createMealInfos(readMealJsonFile("/home/crocobob/cau-meal-crawler/Doc/WeeklyMealData.json"));
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

        try{
            convertJsonNodeToMealInfos(getJsonNode(jsonString, "results"););
            return "Server : Update Meals Successfully.";
        }catch(JSONException e){
            return "Failed to Update Meals. \n" + e.getMessage();
        }
    }

    private void convertJsonNodeToMealInfos(JsonNode resultNode) {
        for (JsonNode result : resultNode) { // result = date, cafeterias
            divideInCafeteria(result.get("cafeterias"), result.get("date").asText());
        }
    }

    private void divideInCafeteria(JsonNode cafeteriaNode, String date) {
        for (JsonNode cafeteria : cafeteriaNode) { // cafeteria = cafeteriaName, meals
            divideInMeal(cafeteria.get("meals"), date, cafeteria.get("cafeteriaName").asText());
        }
    }

    private void divideInMeal(JsonNode mealNode, String date, String cafeteriaName) {
        if(cafeteriaName.equals("University Club(102관11층)")){
            for (JsonNode meal : mealNode) { // meal = mealType, menus
                divideInMenu(
                        meal.get("menus"),
                        meal.get("startTime").toString(),
                        meal.get("endTime").toString(),
                        date, cafeteriaName,
                        meal.get("mealType").toString());
            }
        }else{
            for (JsonNode meal : mealNode) { // meal = mealType, menus
                divideInMenu_102(
                        meal.get("menus"),
                        meal.get("startTime").toString(),
                        meal.get("endTime").toString(),
                        date, cafeteriaName,
                        meal.get("mealType").toString());
            }
        }

    }

    private void divideInMenu(JsonNode menuNode, String date, String startTime, String endTime, String cafeteriaName, String mealType) {
        for (JsonNode menu : menuNode) {
            JSONObject menuObject = new JSONObject(menu.toString());
            JSONArray menuList = menuObject.getJSONArray("menu");

            String[] menuStringList = new String[menuList.length()];
            for (int i = 0; i < menuList.length(); i++) {
                menuStringList[i] = menuList.get(i).toString();
            }
            String menuString = String.join(",", menuStringList);

            // 303관 단품 컷
            if(cafeteriaName.equals("학생식당(303관B1층)") && menuString.length() < 15) continue;

            makeMealInfoObjectAndSaveInDB(date, startTime, endTime, cafeteriaName, mealType, menuString);
        }
    }

    private void divideInMenu_102(JsonNode menuNode, String date, String startTime, String endTime, String cafeteriaName, String mealType){
        List<String> menuStringList = new ArrayList<>();
        for (JsonNode menu : menuNode) {
            JSONObject menuObject = new JSONObject(menu.toString());
            JSONArray menuList = menuObject.getJSONArray("menu");

            for (int i = 0; i < menuList.length(); i++) {
                menuStringList.add(menuList.get(i).toString());
            }
        }
        String menuString = String.join(",", convertListToArray(menuStringList));

        makeMealInfoObjectAndSaveInDB(date, startTime, endTime, cafeteriaName, mealType, menuString);
    }

    private void makeMealInfoObjectAndSaveInDB(String date, String startTime, String endTime, String cafeteriaName, String mealType, String menuString) {
        MealInfo_AfterProcess mealInfo = new MealInfo_AfterProcess(
                date,
                makeDueTime(
                        cleaningStringToOnlyTime(startTime),
                        cleaningStringToOnlyTime(endTime)),
                reduceCafeteriaName(cafeteriaName),
                cleaningStringToOnlyKorean(mealType),
                eraseStarCharToComma(menuString)
        );
        repo.save(mealInfo);
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
            case "생활관식당(블루미르308관)" -> "308관";
            case "생활관식당(블루미르309관)" -> "309관";
            case "참슬기식당(310관 B4층)" -> "310관";
            case "학생식당(303관B1층)" -> "303관";
            case "University Club(102관11층)" -> "102관";
            default -> name;
        };
    }

    private String cleaningStringToOnlyKorean(String str){
        return str.replaceAll("[^가-힣]", "");
    }

    private String cleaningStringToOnlyTime(String str){ return str.replace("\"",""); }

    private String[] convertListToArray(List<String> list){
        return list.toArray(new String[list.size()]);
    }

    private String eraseStarCharToComma(String str){ return str.replace("*","&"); }

    private String makeDueTime(String start, String end){ return start + " - " + end; }
}
