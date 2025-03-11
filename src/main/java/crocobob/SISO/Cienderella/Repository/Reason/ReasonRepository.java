package crocobob.SISO.Cienderella.Repository.Reason;

import crocobob.SISO.Cienderella.Domain.Reason;

import java.util.List;
import java.util.Optional;

public interface ReasonRepository {
    Reason save(Reason reason);
    List<Reason> findAll();
    Optional<Reason> findById(long id);
    List<Reason> findByValidEquals(boolean valid);
    void delete(Reason Reason);
}