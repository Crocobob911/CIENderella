package crocobob.CIENderella.service;


import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Content.ContentRepository_SpringDataJpa;
import crocobob.CIENderella.repository.IntegrationRepository;
import crocobob.CIENderella.repository.IntegrationRepository_SpringDataJpa;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private CienderellaService service;

    @Autowired
    private SpringTemplateEngine engine;

    @Autowired
    private IntegrationRepository repo;

    @Autowired
    private ContentRepository contentRepo;
    @Autowired
    private ReasonRepository reasonRepo;
    @Autowired
    private WriterRepository writerRepo;


    @Test
    public void generateText_Test(){


        System.out.println(service.generateText(LocalDate.now(),"reason1", "writer1"));


/*        Context context = new Context();

        context.setVariable("date", LocalDate.now());
        context.setVariable("reason", "reason1");
        context.setVariable("writer", "writer1");

        System.out.println(engine.process("formPractice",context));*/
    }
}