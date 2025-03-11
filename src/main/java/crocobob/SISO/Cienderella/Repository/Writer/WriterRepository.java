package crocobob.SISO.Cienderella.Repository.Writer;

import crocobob.SISO.Cienderella.Domain.Writer;

import java.util.List;
import java.util.Optional;

public interface WriterRepository {
    Writer save(Writer writer);
    List<Writer> findAll();
    Optional<Writer> findById(long id);
    List<Writer> findByValidEquals(boolean valid);
    void delete(Writer writer);
}