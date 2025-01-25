package com.crocobob.CIENderella.repository;

import com.crocobob.CIENderella.domain.Reason;
import com.crocobob.CIENderella.domain.RequestInfo;
import com.crocobob.CIENderella.domain.Writer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NaiveRequestInfoRepository implements RequestInfoRepository {

    @Override
    public void insertRequestInfo(RequestInfo info) {

    }

    @Override
    public Optional<RequestInfo> getRequestInfo() {
        return Optional.empty();
    }

    @Override
    public void insertWriter(Writer writer) {

    }

    @Override
    public Optional<Writer> getAnyWriter() {
        return Optional.empty();
    }

    @Override
    public void insertReason(Reason reason) {

    }

    @Override
    public Optional<Reason> getAnyReason() {
        return Optional.empty();
    }
}
