package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.CIENderella.Exception.DBEntityNotFoundException;
import crocobob.CIENderella.Exception.InvalidContentTimeDomainException;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import crocobob.CIENderella.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

@Service
public class CienderellaService {
    private final TextGenerator textGenerator;
    private final CamApiService camApiService;

    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;

    private String postTimeOfToday = "22:00";

    private Random rand = new Random();

    public CienderellaService(WriterRepository writerRepo, ReasonRepository reasonRepo, ContentRepository contentRepo, CamApiService camApiService, TextGenerator textGenerator) {
        this.writerRepo = writerRepo;
        this.reasonRepo = reasonRepo;
        this.contentRepo = contentRepo;
        this.camApiService = camApiService;
        this.textGenerator = textGenerator;
    }

    public Form getForm() throws JsonProcessingException {
        var todayContent = getContent();
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
    public void generatePostTimeOfToday() {
        var content = getContent();
        postTimeOfToday = generateRandomPostTime(content.getStartTime(), content.getEndTime());
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

    public Reason saveReason(ReasonDTO dto) {
        Reason reason = new Reason(dto.getText(), true);

        return reasonRepo.save(reason);
    }

    public Writer saveWriter(WriterDTO dto) {
        Writer writer = new Writer(dto.getText(), true);

        return writerRepo.save(writer);
    }

    public Content getContent() {
        return contentRepo.findTopByOrderByIdDesc()
                .orElseThrow(() -> new DBEntityNotFoundException("<< Content not found >>"));
    }

    public Reason getReason(long id) {
        return reasonRepo.findById(id)
                .orElseThrow(() -> new DBEntityNotFoundException("<< No Reason with id : " + id + " found. >>"));
    }

    public Writer getWriter(long id) {
        return writerRepo.findById(id)
                .orElseThrow(() -> new DBEntityNotFoundException("<< No Writer with id : " + id + " found. >>"));
    }

    public List<Reason> getAllReasons() {
        var reasons = reasonRepo.findAll();
        if(reasons.isEmpty()) throw new DBEntityNotFoundException("<< There is no reason >>");
        else return reasons;
    }

    public List<Writer> getAllWriters() {
        var writers = writerRepo.findAll();
        if(writers.isEmpty()) throw new DBEntityNotFoundException("<< There is no writer >>");
        else return writers;
    }


    public void patchUpdateContent(ContentDTO newContent) {
        Content oldContent = getContent();

        if(newContent.getStartTime() != null) oldContent.setStartTime(newContent.getStartTime());
        if(newContent.getEndTime() != null) oldContent.setEndTime(newContent.getEndTime());
        if(newContent.getPassword() != null) oldContent.setPassword(newContent.getPassword());
        if(newContent.getStatus() != null) oldContent.setStatus(newContent.getStatus());
        if(newContent.getTitle() != null) oldContent.setTitle(newContent.getTitle());
        if(newContent.getText() != null) oldContent.setText(newContent.getText());

        contentRepo.save(oldContent);
    }

    public void patchUpdateReason(long reasonId, Reason newReason) {
        Reason oldReason = getReason(reasonId);

        if(newReason.getText() != null) oldReason.setText(newReason.getText());
        if(newReason.getValid() != null) oldReason.setValid(newReason.getValid());

        reasonRepo.save(oldReason);
    }

    public void patchUpdateWriter(long writerId, Writer newWriter) {
        Writer oldWriter = getWriter(writerId);

        if(newWriter.getText() != null) oldWriter.setText(newWriter.getText());
        if(newWriter.getValid() != null) oldWriter.setValid(newWriter.getValid());

        writerRepo.save(oldWriter);
    }



    private Reason findAnyReason(){
        var validReasons = reasonRepo.findByValidEquals(true);
        if(validReasons.isEmpty()) throw new DBEntityNotFoundException("<< There is no valid Reason in DB. >>");
        else return validReasons.get(generateRandIndex(validReasons.size()));
    }

    private Writer findAnyWriter(){
        var validWriters = writerRepo.findByValidEquals(true);
        if(validWriters.isEmpty()) throw new DBEntityNotFoundException("<< There is no valid Writer in DB. >>");
        else return validWriters.get(generateRandIndex(validWriters.size()));
    }

    private int generateRandIndex(int num){
        return rand.nextInt(num);
    }

    public void deleteWriter(Long id) {
        writerRepo.delete(writerRepo.findById(id).orElseThrow(() -> new DBEntityNotFoundException("<< No Writer found >>")));
    }

    public void deleteReason(Long id) {
        reasonRepo.delete(reasonRepo.findById(id).orElseThrow(() -> new DBEntityNotFoundException("<< No Reason found >>")));
    }
}
