package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.CIENderella.domain.cienderella.CamApiResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TextGenerator {

    public String generateTitle(String title) {
        LocalDate today = LocalDate.now();

        int day = today.getDayOfMonth();
        if(LocalDateTime.now().getHour() <= 5)  day -= 1;

        return title
                .replace("{month}", Integer.toString(today.getMonth().getValue()))
                .replace("{day}", Integer.toString(day));
    }

    public String generateContent(String content, String reason, String writer, CamApiResponse response) throws JsonProcessingException {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        String writerAndPeople = generateWriterAndPeople(writer, response);

        return content
                .replace("{next_month}",Integer.toString(tomorrow.getMonth().getValue()))
                .replace("{next_day}", Integer.toString(tomorrow.getDayOfMonth()))
                .replace("{writer}", writerAndPeople)
                .replace("{reason}", reason);
    }

    private static String generateWriterAndPeople(String writer, CamApiResponse response) {

        String returnString = writer;

        if(response.getIsPeopleThere())
            if(response.getPeopleCount() >= 2)
                returnString += " 외 " + (response.getPeopleCount()-1) + "인";

        return returnString;
    }
}