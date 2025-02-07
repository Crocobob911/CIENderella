package crocobob.CIENderella.service;

import org.thymeleaf.context.Context;
import crocobob.CIENderella.domain.*;
import crocobob.CIENderella.repository.IntegrationRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CienderellaService {
    private final SpringTemplateEngine templateEngine;
    private IntegrationRepository repo;

    public CienderellaService(IntegrationRepository repo, SpringTemplateEngine templateEngine) {
        this.repo = repo;
        this.templateEngine = templateEngine;
    }

    public Optional<Form> getForm() {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라
        var todayContent = repo.findContent().orElseThrow();

        return Optional.of(
                new Form(todayContent.getTitle(),
                        todayContent.getPassword(),
                        generateText(LocalDate.now(),
                            repo.findAnyReason().orElseThrow().getText(),
                            repo.findAnyWriter().orElseThrow().getText())
                ));
    }

    public String generateText(LocalDate date, String reason, String writer) {

        Context context = new Context();

        context.setVariable("date", date);
        context.setVariable("reason", reason);
        context.setVariable("writer", writer);

        return templateEngine.process("formPractice", context);
    }

    public void saveContent(Content content) {
        repo.save(content);
    }

    public void saveReason(Reason reason) {
        repo.save(reason);
    }

    public void saveWriter(Writer writer) {
        repo.save(writer);
    }

    public Optional<Content> getContent() {
        return repo.findContent();
    }

    public List<Reason> getAllReasons() {
        return repo.findAllReasons();
    }

    public List<Writer> getAllWriters() {
        return repo.findAllWriters();
    }

    public void systemOnOff(boolean onOff) {
        repo.updateContentStatus(onOff);
    }

    public void updateReasonIsValid(Reason reason, boolean isValid) {
        repo.updateValid(reason, isValid);
    }

    public void updateWriterIsValid(Writer writer, boolean isValid) {
        repo.updateValid(writer, isValid);
    }
}