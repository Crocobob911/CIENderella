package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Meal.MealRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import crocobob.CIENderella.service.Meal.MealParseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CienderellaServiceTest {


    @Test
    void generatePostTimeOfToday_test() {
        Random rand = new Random();
        int randomHour;

        int startTime = 21;
        int endTime = 0;

        if(startTime <= endTime) {
            randomHour = rand.nextInt(endTime - startTime) + startTime;
        }else{
            if(endTime == 0 || rand.nextBoolean()) {
                randomHour = rand.nextInt(24-startTime) + startTime;
            }else{
                randomHour = rand.nextInt(endTime);
            }
        }
        int randomMinute = rand.nextInt(6)*10;

        System.out.println(String.format("%02d:%02d", randomHour, randomMinute));
    }
}