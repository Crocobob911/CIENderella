package crocobob.SISO.service;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MealParseServiceTest {
    @Test
    public void testParse() {
        try {
            ClassPathResource resource = new ClassPathResource("mealJsonPath.txt");
            String path =  new String(Files.readAllBytes(Paths.get(resource.getURI())));

            System.out.println(Files.readString(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
