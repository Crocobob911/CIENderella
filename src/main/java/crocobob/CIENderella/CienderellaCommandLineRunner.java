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
        // For Test
        repo.save(new Content("cien-password", true, "title", "text"));

        repo.save(new Reason("게임 분석 스터디", true));
        repo.save(new Reason("창립제 준비", true));
        repo.save(new Reason("C# 프로그래밍 튜터링", true));
        repo.save(new Reason("게임잼 진행", true));

        repo.save(new Writer("이유정", true));
        repo.save(new Writer("김현수", true));
        repo.save(new Writer("김준", true));
        repo.save(new Writer("최선재", true));
    }
}