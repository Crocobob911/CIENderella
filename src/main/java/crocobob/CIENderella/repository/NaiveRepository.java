package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Writer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

public class NaiveRepository implements CienderellaRepository {

    // 여기서 List나 Map 써서 나이브하게 저장하는 게 좋을 듯?
    // 일단은 테스트니까.

    Form form;

    Content content;

    Map<Long, Writer> writers = new HashMap<>();
    long writersIndex = 0;

    Map<Long, Reason> reasons = new HashMap<>();
    long reasonsIndex = 0;

//    List<FormInfo> forms;
//    List<Reason> reasons;
//    List<Writer> writers;



    @Override
    public void addContent(Content content) {
        this.content = content;
    }

    @Override
    public Optional<Content> getContent() {
        return Optional.ofNullable(content);
    }

    @Override
    public void deleteContent() {
        content = null;
    }


    @Override
    public void addWriter(Writer writer) {
        writers.put(++writersIndex, writer);
    }

    @Override
    public Optional<Writer> getAnyWriter() {
        return Optional.ofNullable(writers.get(randIndex(writersIndex)));
    }

    @Override
    public List<Writer> getAllWriter() {
        return new ArrayList<>(writers.values());
    }

    @Override
    public void deleteWriter(long id) {
        writers.remove(id);
    }


    @Override
    public void addReason(Reason reason) {
        reasons.put(++reasonsIndex, reason);
    }

    @Override
    public Optional<Reason> getAnyReason() {
        return Optional.ofNullable(reasons.get(randIndex(reasonsIndex)));
    }

    @Override
    public List<Reason> getAllReason() {
        return new ArrayList<>(reasons.values());
    }

    @Override
    public void deleteReason(long id) {
        reasons.remove(id);
    }


    private long randIndex(long num){
        return (long) (Math.random()*num+1);
    }

    @Override
    public void clear() {
        form = null;
        content = null;
        writers.clear();
        writersIndex = 0;
        reasons.clear();
        reasonsIndex = 0;
    }
}
