package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NaiveRepositoryTest {

    NaiveRepository repo = new NaiveRepository();

    @BeforeEach
    void beforeEach() {
//        repo.addContent(new Content("title", "text"));
//
//        repo.addReason(new Reason("reason1"));
//        repo.addReason(new Reason("reason2"));
//        repo.addReason(new Reason("reason3"));
//
//        repo.addWriter(new Writer("writer1"));
//        repo.addWriter(new Writer("writer2"));
//        repo.addWriter(new Writer("writer3"));
    }

    @AfterEach
    void afterEach() {
        repo.clear();
    }


    @Test
    void generateForm() {
//        System.out.println(repo.generateForm());
//        System.out.println(repo.generateForm());
//        System.out.println(repo.generateForm());
//        System.out.println(repo.generateForm());
//        System.out.println(repo.generateForm());
    }

    @Test
    void addContent() {
    }

    @Test
    void getContent() {
        System.out.println(repo.content.toString());
    }

    @Test
    void deleteContent() {
    }

    @Test
    void addWriter() {
    }

    @Test
    void getAnyWriter() {
        System.out.println(repo.getAnyWriter().toString());
        System.out.println(repo.getAnyWriter().toString());
        System.out.println(repo.getAnyWriter().toString());
        System.out.println(repo.getAnyWriter().toString());
        System.out.println(repo.getAnyWriter().toString());
    }

    @Test
    void getAllWriter() {
        repo.getAllWriter().stream()
                .forEach(System.out::println);
    }

    @Test
    void deleteWriter() {
    }

    @Test
    void addReason() {
    }

    @Test
    void getAnyReason() {
        System.out.println(repo.getAnyReason().toString());
        System.out.println(repo.getAnyReason().toString());
        System.out.println(repo.getAnyReason().toString());
        System.out.println(repo.getAnyReason().toString());
        System.out.println(repo.getAnyReason().toString());
    }

    @Test
    void getAllReason() {
        repo.getAllReason().stream()
                .forEach(System.out::println);
    }

    @Test
    void deleteReason() {
    }
}