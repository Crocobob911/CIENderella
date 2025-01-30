package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.JpaIntegrationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class DataManagementService {
    private JpaIntegrationRepository repo;

    public DataManagementService(JpaIntegrationRepository repo) {
        this.repo = repo;
    }

    public Optional<Form> getForm() {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라
        var todayContent = repo.findContent(1).orElseThrow();

        return Optional.of(new Form(
                todayContent.getStatus(),
                todayContent.getTitle(),
                todayContent.getText(),
                LocalDate.now(),
                todayContent.getPassward(),
                repo.findAnyReason().orElseThrow(),
                repo.findAnyWriter().orElseThrow(),
                LocalTime.now()
        ));
    }

    public void insertContent(Content content){
        repo.insert(content);
    }

    public void insertReason(Reason reason){
        repo.insert(reason);
    }

    public void insertWriter(Writer writer){
        repo.insert(writer);
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
