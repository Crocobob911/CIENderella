package crocobob.CIENderella;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;

@Component
public class TextGenerator {
    public TextGenerator(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    private final SpringTemplateEngine templateEngine;

    public String generateText(LocalDate date, String reason, String writer) {

        Context context = new Context();

        context.setVariable("date", date);
        context.setVariable("reason", reason);
        context.setVariable("writer", writer);

        return templateEngine.process("formPractice", context);
    }
}
