package crocobob.CIENderella.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealServiceTest {

    private MealService service = new MealService();

    @Test
    public void test_addMeal() {

        String inputJson
                = "";

        System.out.println(service.addMeal_test(inputJson));

    }

}