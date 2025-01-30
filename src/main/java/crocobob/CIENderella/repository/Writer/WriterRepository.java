package crocobob.CIENderella.repository.Writer;

import crocobob.CIENderella.domain.Writer;

import java.util.Optional;

public interface WriterRepository {
    void save(Writer writer);
    Optional<Writer> findById(long id);
    Optional<Writer> findByValidEquals(boolean valid);
    void delete(Writer writer);
}
