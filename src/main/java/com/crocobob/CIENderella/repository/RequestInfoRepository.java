package com.crocobob.CIENderella.repository;

import com.crocobob.CIENderella.domain.Reason;
import com.crocobob.CIENderella.domain.RequestInfo;
import com.crocobob.CIENderella.domain.Writer;

import java.util.Optional;

public interface RequestInfoRepository {

    void insertRequestInfo(RequestInfo info);
    Optional<RequestInfo> getRequestInfo();

    void insertWriter(Writer writer);
    Optional<Writer> getAnyWriter();

    void insertReason(Reason reason);
    Optional<Reason> getAnyReason();
}
