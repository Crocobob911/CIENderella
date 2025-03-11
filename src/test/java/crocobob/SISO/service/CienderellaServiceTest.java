package crocobob.SISO.service;

import org.junit.jupiter.api.Test;

import java.util.Random;

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