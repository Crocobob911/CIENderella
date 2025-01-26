package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.FormInfo;
import crocobob.CIENderella.domain.Writer;

import java.util.Optional;

public interface CienderellaRepository {

    void insertFormInfo(FormInfo info);
    Optional<FormInfo> getFormInfo();

    void createWriter(Writer writer);
    Optional<Writer> getAnyWriter();
    void deleteWriter(long id);

    void createReason(Reason reason);
    Optional<Reason> getAnyReason();
    void deleteReason(long id);
}
