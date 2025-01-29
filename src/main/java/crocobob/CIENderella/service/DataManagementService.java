package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.CienderellaRepository;
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
        var todayContent = repo.find(Content.class, 1).orElseThrow();

        return Optional.of(new Form(
                todayContent.getStatus(),
                todayContent.getTitle(),
                todayContent.getText(),
                LocalDate.now(),
                todayContent.getPassward(),
                repo.getAnyReason().orElseThrow(),
                repo.getAnyWriter().orElseThrow(),
                LocalTime.now()
        ));
    }

    public void createContent(Content content){
        repo.addContent(content);
    }

    public void createReason(Reason reason){
        repo.addReason(reason);
    }

    public void createWriter(Writer writer){
        repo.addWriter(writer);
    }
}
