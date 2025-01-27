package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.CienderellaRepository;
import crocobob.CIENderella.repository.NaiveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataManagementServiceTest {

    CienderellaRepository repo;
    DataManagementService service;

    @BeforeEach
    void beforeEach(){
        repo = new NaiveRepository();
        service = new DataManagementService(repo);

        repo.addContent(new Content("title", "text"));

        repo.addReason(new Reason("reason1"));
        repo.addReason(new Reason("reason2"));
        repo.addReason(new Reason("reason3"));

        repo.addWriter(new Writer("writer1"));
        repo.addWriter(new Writer("writer2"));
        repo.addWriter(new Writer("writer3"));
    }

    @AfterEach
    void afterEach(){
        repo.clear();
    }

    @Test
    void getForm() {
        System.out.println(service.getForm());
        System.out.println(service.getForm());
        System.out.println(service.getForm());
        System.out.println(service.getForm());
        System.out.println(service.getForm());
    }

    @Test
    void createContent() {
    }

    @Test
    void createReason() {
    }

    @Test
    void createWriter() {
    }
}