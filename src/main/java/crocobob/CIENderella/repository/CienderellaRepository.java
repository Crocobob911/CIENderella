package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;

import java.util.List;
import java.util.Optional;

public interface CienderellaRepository {

//    Optional<Form> generateForm();

    void addContent(Content content);
    Optional<Content> getContent();
    void deleteContent();

    void addWriter(Writer writer);
    Optional<Writer> getAnyWriter();
    List<Writer> getAllWriter();
    void deleteWriter(long id);

    void addReason(Reason reason);
    Optional<Reason> getAnyReason();
    List<Reason> getAllReason();
    void deleteReason(long id);

    public void clear();
}
