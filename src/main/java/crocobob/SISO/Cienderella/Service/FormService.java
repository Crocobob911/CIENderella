package crocobob.SISO.Cienderella.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.SISO.Cienderella.Domain.Form;
import crocobob.SISO.Cienderella.Domain.Reason;
import crocobob.SISO.Cienderella.Domain.Writer;
import crocobob.SISO.Exception.DBEntityNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FormService {
    private final ContentService contentService;
    private final WriterService writerService;
    private final ReasonService reasonService;
    private final TextGenerator textGenerator;
    private final CamApiService camApiService;

    private Random rand = new Random();
    private String postTimeOfToday = "22:00";

    public FormService(ContentService contentService, WriterService writerService, ReasonService reasonService, TextGenerator textGenerator, CamApiService camApiService) {
        this.contentService = contentService;
        this.writerService = writerService;
        this.reasonService = reasonService;
        this.textGenerator = textGenerator;
        this.camApiService = camApiService;
    }

    public Form getForm() throws JsonProcessingException {
        var todayContent = contentService.getContent();
        var tmpWriter = findAnyWriter().getText();

        return new Form(postTimeOfToday,
                todayContent.getStatus(),
                textGenerator.generateTitle(todayContent.getTitle()),
                tmpWriter,
                todayContent.getPassword(),
                textGenerator.generateContent(
                        todayContent.getText(),
                        findAnyReason().getText(),
                        tmpWriter,
                        camApiService.getCamApiResponse()
                ));
    }

    @Scheduled(cron = "0 0 6 * * ?")
    private void generatePostTimeOfToday() {
        var content = contentService.getContent();
        postTimeOfToday = generateRandomPostTime(content.getStartTime(), content.getEndTime());
    }

    private Writer findAnyWriter(){
        var validWriters = writerService.getValidWriters();
        if(validWriters.isEmpty()) throw new DBEntityNotFoundException("<< There is no valid Writer in DB. >>");
        else return validWriters.get(generateRandIndex(validWriters.size()));
    }

    private Reason findAnyReason(){
        var validReasons = reasonService.getValidReasons();
        if(validReasons.isEmpty()) throw new DBEntityNotFoundException("<< There is no valid Reason in DB. >>");
        else return validReasons.get(generateRandIndex(validReasons.size()));
    }

    private int generateRandIndex(int num){
        return rand.nextInt(num);
    }

    private String generateRandomPostTime(int startTime, int endTime) {
        Random rand = new Random();
        int randomHour;

        if(startTime <= endTime) {
            randomHour = rand.nextInt(endTime - startTime) + startTime;
        }else{
            if(endTime == 0 || rand.nextBoolean()) {
                randomHour = rand.nextInt(24-startTime) + startTime;
            }else{
                randomHour = rand.nextInt(endTime);
            }
        }
        int randomMinute = rand.nextInt(6)*10;

        return String.format("%02d:%02d", randomHour, randomMinute);
    }
}
