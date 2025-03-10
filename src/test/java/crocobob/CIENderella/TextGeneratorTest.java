package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.CIENderella.domain.cienderella.CamApiResponse;
import crocobob.CIENderella.service.TextGenerator;
import org.junit.jupiter.api.Test;

class TextGeneratorTest {

    TextGenerator tg = new TextGenerator();

    @Test
    public void genTest() throws JsonProcessingException {
        String contentText = "\"신청자\":{writer}, \"신청 사유\":{reason}";
//        Content content = new Content("password", true, "title", contentText);
        String writer = "김현수";
        String reason = "롤 다이아 등반";

        System.out.println(
                tg.generateContent(contentText, reason, writer, new CamApiResponse(3,true))
        );
    }
}