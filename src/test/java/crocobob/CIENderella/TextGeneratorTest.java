package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.CIENderella.domain.Content;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TextGeneratorTest {

    TextGenerator tg = new TextGenerator();

    @Test
    public void genTest() throws JsonProcessingException {
        String contentText = "\"신청자\":{writer}, \"신청 사유\":{reason}";
//        Content content = new Content("password", true, "title", contentText);
        String writer = "김현수";
        String reason = "수면";

        System.out.println(
                tg.generateText_Json(LocalDate.now(), contentText, reason, writer)
        );
    }
}