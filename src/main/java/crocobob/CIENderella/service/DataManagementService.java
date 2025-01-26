package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.FormInfo;
import crocobob.CIENderella.repository.CienderellaRepository;
import org.springframework.stereotype.Service;

@Service
public class DataManagementService {
    private CienderellaRepository repo;

    public DataManagementService(CienderellaRepository repo) {
        this.repo = repo;
    }

    public void createRequestInfo(FormInfo formInfo) {
        // -> 이거 "동적 치환" 뭐시기 알아본다고 하더라
    }
}
