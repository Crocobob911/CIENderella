package crocobob.CIENderella;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CienderellaCommandLineRunner implements CommandLineRunner {

    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;

    public CienderellaCommandLineRunner(ContentRepository contentRepo, ReasonRepository reasonRepo, WriterRepository writerRepo) {
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // For Test
        contentRepo.save(new Content("default_password", true,
                "{month}/{date} CIEN 사용신청입니다.",
                "{writer}, {reason}, {month}, {date}"));

        reasonRepo.save(new Reason("게임 분석 스터디", true));
        reasonRepo.save(new Reason("창립제 준비", true));
        reasonRepo.save(new Reason("C# 프로그래밍 튜터링", true));
        reasonRepo.save(new Reason("게임잼 진행", true));

        writerRepo.save(new Writer("이유정", true));
        writerRepo.save(new Writer("김현수", true));
        writerRepo.save(new Writer("김준", true));
        writerRepo.save(new Writer("최선재", true));
    }
}