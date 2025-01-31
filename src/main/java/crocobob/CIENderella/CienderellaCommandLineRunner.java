package crocobob.CIENderella;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.IntegrationRepository_SpringDataJpa;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CienderellaCommandLineRunner implements CommandLineRunner {

    private IntegrationRepository_SpringDataJpa repo;

    public CienderellaCommandLineRunner(IntegrationRepository_SpringDataJpa repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
//        repo.save(new Content(1L, "password",true,"hello","hello"));

        repo.save(new Reason(1L, "reason1", true));

//        System.out.println(repo.findAnyReason().toString());

//        System.out.println(repo.findAnyWriter());

    }
}