package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TextGenerator {

    public String generateTitle(String title) {
        LocalDate today = LocalDate.now();

        return title
                .replace("{month}", Integer.toString(today.getMonth().getValue()))
                .replace("{day}", Integer.toString(today.getDayOfMonth()));
    }

    public String generateContent(String content, String reason, String writer) throws JsonProcessingException {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        return content
                .replace("{next_month}",Integer.toString(tomorrow.getMonth().getValue()))
                .replace("{next_day}", Integer.toString(tomorrow.getDayOfMonth()))
                .replace("{writer}", writer)
                .replace("{reason}", reason);
    }
}