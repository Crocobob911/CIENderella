package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.CienderellaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataManagementService {
    private CienderellaRepository repo;

    public DataManagementService(CienderellaRepository repo) {
        this.repo = repo;
    }

    public Optional<Form> getForm() {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라

        return repo.generateForm();
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
