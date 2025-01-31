package crocobob.CIENderella.repository.Reason;

import crocobob.CIENderella.domain.Reason;

import java.util.List;
import java.util.Optional;

public interface ReasonRepository {
    Reason save(Reason reason);
    Optional<Reason> findById(long id);
    List<Reason> findByValidEquals(boolean valid);
    void delete(Reason Reason);
}