package crocobob.CIENderella;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Notice.NoticeRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import crocobob.CIENderella.service.Meal.MealParseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CienderellaCommandLineRunner implements CommandLineRunner {

    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;
    private final NoticeRepository noticeRepo;
    private final MealParseService mealService;

    public CienderellaCommandLineRunner(ContentRepository contentRepo, ReasonRepository reasonRepo, WriterRepository writerRepo, NoticeRepository noticeRepo, MealParseService mealService) {
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
        this.noticeRepo = noticeRepo;
        this.mealService = mealService;
    }

    @Override
    public void run(String... args) throws Exception {
        // For Test
        contentRepo.save(new Content(21, 23,
                "cien14789*", true,
                "{month}/{day}_CIEN 사용신청입니다.",
                "동아리명: CIEN\n" +
                        "사용 날짜 및 시간 :{next_month}월 {next_day}일 00시~07시\n" +
                        "신청자 : {writer}\n" +
                        "사유 : {reason}"));

        reasonRepo.save(new Reason("게임 개발 회의", true));

        writerRepo.save(new Writer("이유정", true));

        mealService.createWeeklyMealData();
    }
}