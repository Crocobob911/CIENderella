package crocobob.SISO;

import crocobob.SISO.Kiosk.Domain.Gallery.GalleryInfo;
import crocobob.SISO.Cienderella.Domain.Content;
import crocobob.SISO.Cienderella.Domain.Reason;
import crocobob.SISO.Cienderella.Domain.Writer;
import crocobob.SISO.Cienderella.Repository.Content.ContentRepository;
import crocobob.SISO.Kiosk.Repository.GalleryInfo.GalleryInfoRepository;
import crocobob.SISO.Kiosk.Repository.Notice.NoticeRepository;
import crocobob.SISO.Cienderella.Repository.Reason.ReasonRepository;
import crocobob.SISO.Cienderella.Repository.Writer.WriterRepository;
import crocobob.SISO.Kiosk.Service.Meal.MealParseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SISOCommandLineRunner implements CommandLineRunner {

    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;
    private final NoticeRepository noticeRepo;
    private final MealParseService mealService;
    private final GalleryInfoRepository galRepo;

    public SISOCommandLineRunner(ContentRepository contentRepo, ReasonRepository reasonRepo, WriterRepository writerRepo, NoticeRepository noticeRepo, MealParseService mealService, GalleryInfoRepository galRepo) {
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
        this.noticeRepo = noticeRepo;
        this.mealService = mealService;
        this.galRepo = galRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // For Test
//        contentRepo.save(new Content(21, 23,
//                "cien14789*", true,
//                "{month}/{day}_CIEN 사용신청입니다.",
//                "동아리명: CIEN\n" +
//                        "사용 날짜 및 시간 :{next_month}월 {next_day}일 00시~07시\n" +
//                        "신청자 : {writer}\n" +
//                        "사유 : {reason}"));

//        reasonRepo.save(new Reason("게임 개발 회의", true));
//        reasonRepo.save(new Reason("신입생 면접 준비", true));

//        writerRepo.save(new Writer("이유정", true));
//        writerRepo.save(new Writer("김현우", true));
//        writerRepo.save(new Writer("노영욱", true));
//        writerRepo.save(new Writer("석재혁", true));
//        writerRepo.save(new Writer("김준", true));

//        mealService.createWeeklyMealData();

//        galRepo.save(new GalleryInfo(5, 10, 5));
    }
}
