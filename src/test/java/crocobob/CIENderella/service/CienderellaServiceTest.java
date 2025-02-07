package crocobob.CIENderella.service;


import crocobob.CIENderella.repository.IntegrationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;

@SpringBootTest
class CienderellaServiceTest {

//    @InjectMocks
//    private CienderellaService service;
//
//    @Mock
//    private SpringTemplateEngine templateEngine;
//
//    @Mock
//    private IntegrationRepository repo;

    @Test
    public void generateText_Test(){
        System.out.println("wht?");
//        SpringTemplateEngine engine = new SpringTemplateEngine();

/*        Context context = new Context();

        context.setVariable("date", LocalDate.now());
        context.setVariable("reason", "reason1");
        context.setVariable("writer", "writer1");*/

//        System.out.println(engine.process("formPractice",context));
    }
}