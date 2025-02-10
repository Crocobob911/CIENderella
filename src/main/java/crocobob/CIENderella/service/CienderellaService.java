package crocobob.CIENderella.service;

import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.thymeleaf.context.Context;
import crocobob.CIENderella.domain.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CienderellaService {
    private final SpringTemplateEngine templateEngine;

    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;

    private Random rand = new Random();

    public CienderellaService(SpringTemplateEngine templateEngine, ContentRepository contentRepo, ReasonRepository reasonRepo, WriterRepository writerRepo) {
        this.templateEngine = templateEngine;
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
    }

    public Form getForm() {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라
        var todayContent = getContent();

        return new Form(todayContent.getTitle(),
                        todayContent.getPassword(),
                        generateText(LocalDate.now(),
                            findAnyReason().getText(),
                            findAnyWriter().getText()));
    }

    public String generateText(LocalDate date, String reason, String writer) {

        Context context = new Context();

        context.setVariable("date", date);
        context.setVariable("reason", reason);
        context.setVariable("writer", writer);

        return templateEngine.process("formPractice", context);
    }

    public void saveContent(Content content) {
        contentRepo.save(content);
    }

    public void saveReason(Reason reason) {
        reasonRepo.save(reason);
    }

    public void saveWriter(Writer writer) {
        writerRepo.save(writer);
    }

    public Content getContent() {
        return contentRepo.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public Reason getReason(long id) {
        return reasonRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Reason with id : " + id + " found."));
    }

    public Writer getWriter(long id) {
        return writerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Writer with id : " + id + " found."));
    }

    public List<Reason> getAllReasons() {
        var reasons = reasonRepo.findAll();
        if(reasons.isEmpty()) throw new RuntimeException("There is no reason");
        else return reasons;
    }

    public List<Writer> getAllWriters() {
        var writers = writerRepo.findAll();
        if(writers.isEmpty()) throw new RuntimeException("There is no writer");
        else return writers;
    }

    public void systemOnOff(boolean onOff) {
//        repo.updateContentStatus(onOff);
    }

    public void updateContent(Content content) {

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
        if(validReasons.isEmpty()) throw new EntityNotFoundException("There is no valid Reason in DB.");
        else return validReasons.get(generateRandIndex(validReasons.size()));
    }

    private Writer findAnyWriter(){
        var validWriters = writerRepo.findByValidEquals(true);
        if(validWriters.isEmpty()) throw new EntityNotFoundException("There is no valid Writer in DB.");
        else return validWriters.get(generateRandIndex(validWriters.size()));
    }

    private int generateRandIndex(int num){
        return rand.nextInt(num);
    }
}