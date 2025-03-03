package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crocobob.CIENderella.domain.meal.MealInfo_AfterProcess;
import crocobob.CIENderella.domain.meal.MealRoot;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MealService.class)
class MealServiceTest {

    @Autowired
    private MealService service;

    private String jsonString;

    @BeforeEach
    void setUp() {
        try{
            jsonString = Files.readString(Paths.get("src/test/resources/mealJsonExample.json"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testTest() {
        try{
            System.out.println("hello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readJson() {
        try{
            String inputJson = Files.readString(Paths.get("src/test/resources/mealJsonExample.json"));
            assertNotNull(inputJson);
            System.out.println(inputJson);
        }catch (IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test_addMeal(){
        try {
            String inputJson = Files.readString(Paths.get("src/test/resources/mealJsonExample.json"));
            assertNotNull(inputJson);

            System.out.println(addMeal_Test(inputJson));
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public String addMeal_Test(String crawlResultString) {
        ObjectMapper mapper = new ObjectMapper();
        List<MealInfo_AfterProcess> mealInfos = new ArrayList<MealInfo_AfterProcess>();
        try{
            JsonNode mealCrawlResults = mapper.readTree(crawlResultString).get("results");
        }catch (JsonProcessingException e){
            fail(e.getMessage());
        }

        return mealInfos.toString();
    }

    @Test
    public void getJsonNode() {
        List<MealInfo_AfterProcess> mealInfoList = new ArrayList<MealInfo_AfterProcess>();

        var resultNode = service.getJsonNode(jsonString, "results");
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
                            String menuString = "";
                            for(int i = 0; i < menuList.length(); i++){
                                menuString += menuList.get(i).toString() + " ";
                            }
                            MealInfo_AfterProcess mealInfo = new MealInfo_AfterProcess(
                                    result.get("date").asText(),
                                    cafeteria.get("cafeteriaName").asText(),
                                    meal.get("mealType").toString(),
                                    menuString
                            );
                            mealInfoList.add(mealInfo);
                        }catch (JSONException e){
                             fail(e.getMessage());
                        }
                    }
                }
            }
        }

        for(int i = 0; i < mealInfoList.size(); i++){
            System.out.println(mealInfoList.get(i).toString());
        }
    }
}