package crocobob.CIENderella.repository.Writer;

import crocobob.CIENderella.domain.Writer;

import java.util.Optional;

public interface WriterRepository {
    void insert(Writer writer);
    Optional<Writer> find(long id);
    Optional<Writer> findAny();
    void delete(long id);
    void updateValid(long id, boolean isValid);

}
