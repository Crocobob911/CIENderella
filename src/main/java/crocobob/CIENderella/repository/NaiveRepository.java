package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.FormInfo;
import crocobob.CIENderella.domain.Writer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NaiveRepository implements CienderellaRepository {

    // 여기서 List나 Map 써서 나이브하게 저장하는 게 좋을 듯?
    // 일단은 테스트니까.

    @Override
    public void insertFormInfo(FormInfo info) {

    }

    @Override
    public Optional<FormInfo> getFormInfo() {
        return Optional.empty();
    }

    @Override
    public void createWriter(Writer writer) {

    }

    @Override
    public Optional<Writer> getAnyWriter() {
        return Optional.empty();
    }

    @Override
    public void deleteWriter(long id) {

    }

    @Override
    public void createReason(Reason reason) {

    }

    @Override
    public Optional<Reason> getAnyReason() {
        return Optional.empty();
    }

    @Override
    public void deleteReason(long id) {

    }
}
