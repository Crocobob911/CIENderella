package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import crocobob.CIENderella.domain.Content;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;

@Component
public class TextGenerator {

    public String generateText_Json(LocalDate date, String content, String reason, String writer) throws JsonProcessingException {
        return content.replace("{reason}", reason)
                .replace("{writer}", writer);
    }
}