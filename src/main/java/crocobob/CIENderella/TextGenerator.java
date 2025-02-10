package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TextGenerator {

    public String generateTitle(String title, LocalDate date) {
        return title.replace("{month}", date.getMonth().toString())
                .replace("{date}", date.getDayOfMonth());
    }

    public String generateContent(String content, String reason, String writer) throws JsonProcessingException {
        return content.replace("{reason}", reason)
                .replace("{writer}", writer);
    }
}