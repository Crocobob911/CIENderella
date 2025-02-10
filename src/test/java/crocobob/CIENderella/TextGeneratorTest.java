package crocobob.CIENderella;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TextGeneratorTest {
    TextGenerator tg = new TextGenerator();

    @Test
    public void genTest() throws JsonProcessingException {
        System.out.println(
                tg.generateText_Json(LocalDate.now(),
                        "\"신청자\" : {writer}, \"신청 사유\" : {reason}",
                        "김현수",
                        "수면")
        );
    }
}