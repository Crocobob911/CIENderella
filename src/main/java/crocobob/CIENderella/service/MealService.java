package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.domain.meal.MealRoot;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

    public String addMeal(MealRoot mealRoot) {


        return "Server : Update Meals Successfully.";
    }

    public String addMeal_test(String crawlResultString) {
        ObjectMapper mapper = new ObjectMapper();
        List<MealInfo_AfterProcess> mealInfos = new ArrayList<MealInfo_AfterProcess>();

        try {
            JsonNode mealCrawlResults = mapper.readTree(crawlResultString).get("results");
            for (JsonNode mealRoot : mealCrawlResults) {
                JsonNode cafeteriaNode = mealRoot.get("cafeterias");
                for (JsonNode cafeteria : cafeteriaNode) {
                    MealInfo_AfterProcess mealInfo = new MealInfo_AfterProcess();
                    mealInfo.setDate(cafeteria.get("date").asText());
                    mealInfo.setCafeteria(cafeteria.get("cafeteriaName").asText());

                    JsonNode mealNode = cafeteria.get("meal");
                    for (JsonNode meal : mealNode) {
                        mealInfo.setMealType(meal.get("mealType").asText());

                        JsonNode menusNode = mealNode.get("menus");
                        for (JsonNode menu : menusNode) {
                            mealInfo.setMenu(menu.get("menu").toString());
                            mealInfos.add(mealInfo);
                        }
                    }
                }
            }
            return mealInfos.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "DEBUG : [MealService.addMeal_test] wrong input";
        }

    }

    public JsonNode getJsonNode(String jsonString, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readTree(jsonString).get(key);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    public JsonNode getJsonNode(JsonNode jsonNode, String key) {
        ObjectMapper mapper = new ObjectMapper();
        return jsonNode.get(key);
    }
}
