package crocobob.CIENderella;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.IntegrationRepository_SpringDataJpa;
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
/*        // For Test
        repo.save(new Content("password", true, "title", "text"));

        repo.save(new Reason("reason1", true));
        repo.save(new Reason("reason2", true));
        repo.save(new Reason("reason3", true));
        repo.save(new Reason("reason4", true));

        repo.save(new Writer("writer1", true));
        repo.save(new Writer("writer2", true));
        repo.save(new Writer("writer3", true));
        repo.save(new Writer("writer4", true));*/
    }
}