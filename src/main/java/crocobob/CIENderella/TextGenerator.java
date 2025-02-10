package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;

@Component
public class TextGenerator {

    public String generateText_Json(LocalDate date, String content, String reason, String writer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);

        String returnText = node.path("text").asText();
        return returnText
                .replace("{reason}", reason)
                .replace("{writer}", writer);
    }
}
