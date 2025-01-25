package com.crocobob.CIENderella.service;

import com.crocobob.CIENderella.domain.RequestInfo;
import com.crocobob.CIENderella.repository.RequestInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class DataManagementService {
    private RequestInfoRepository repo;

    public DataManagementService(RequestInfoRepository repo) {
        this.repo = repo;
    }

    public void createRequestInfo(RequestInfo requestInfo) {
        // 매개변수로 뭘 받아와야하지? 각 값을 하나하나?
        // 아니면 그냥 본문 String을 통째로?
    }
}
