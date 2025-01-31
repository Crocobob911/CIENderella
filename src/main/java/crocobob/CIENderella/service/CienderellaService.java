package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.IntegrationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class CienderellaService {
    private IntegrationRepository repo;

    public CienderellaService(IntegrationRepository repo) {
        this.repo = repo;
    }

    public Optional<Form> getForm() {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라
        var todayContent = repo.findContent().orElseThrow();

        return Optional.of(new Form(
                todayContent.getStatus(),
                todayContent.getTitle(),
                todayContent.getText(),
                LocalDate.now(),
                todayContent.getPassword(),
                repo.findAnyReason().orElseThrow().getText(),
                repo.findAnyWriter().orElseThrow().getText(),
                LocalTime.now()
        ));
    }

    public void saveContent(Content content){
        repo.save(content);
    }

    public void saveReason(Reason reason){
        repo.save(reason);
    }

    public void saveWriter(Writer writer){
        repo.save(writer);
    }

    public void systemOnOff(boolean onOff){
        repo.updateContentStatus(onOff);
    }

    public void updateReasonIsValid(Reason reason, boolean isValid){
        repo.updateValid(reason, isValid);
    }

    public void updateWriterIsValid(Writer writer, boolean isValid){
        repo.updateValid(writer, isValid);
    }
}