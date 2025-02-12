package crocobob.CIENderella.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import crocobob.CIENderella.Exception.DBEntityNotFoundException;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import crocobob.CIENderella.domain.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

@Service
public class CienderellaService {
    private final TextGenerator textGenerator;

    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;

    private Random rand = new Random();

    public CienderellaService(TextGenerator textGenerator, ContentRepository contentRepo, ReasonRepository reasonRepo, WriterRepository writerRepo) {
        this.textGenerator = textGenerator;
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
    }

    public Form getForm() throws JsonProcessingException {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라
        var todayContent = getContent();

        return new Form(textGenerator.generateTitle(todayContent.getTitle()),
                        todayContent.getPassword(),
                        textGenerator.generateContent(
                                todayContent.getText(),
                            findAnyReason().getText(),
                            findAnyWriter().getText()));
    }

    public void saveReason(Reason reason) {
        reasonRepo.save(reason);
    }

    public void saveWriter(Writer writer) {
        writerRepo.save(writer);
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


    public void patchUpdateContent(Content newContent) {
        Content oldContent = getContent();

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
}