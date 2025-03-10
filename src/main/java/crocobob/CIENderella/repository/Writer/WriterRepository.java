package crocobob.CIENderella.repository.Writer;

import crocobob.CIENderella.domain.cienderella.Writer;

import java.util.List;
import java.util.Optional;

public interface WriterRepository {
    Writer save(Writer writer);
    List<Writer> findAll();
    Optional<Writer> findById(long id);
    List<Writer> findByValidEquals(boolean valid);
    void delete(Writer writer);
}