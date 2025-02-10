package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

class TextGeneratorTest {

    TextGenerator tg = new TextGenerator();

    @Test
    public void genTest() throws JsonProcessingException {
        String contentText = "\"신청자\":{writer}, \"신청 사유\":{reason}";
//        Content content = new Content("password", true, "title", contentText);
        String writer = "김현수";
        String reason = "수면";

        System.out.println(
                tg.generateContent(contentText, reason, writer)
        );
    }
}