package crocobob.CIENderella.repository.Reason;

import crocobob.CIENderella.domain.Reason;

import java.util.List;
import java.util.Optional;

public interface ReasonRepository {
    Reason save(Reason reason);
    List<Reason> findAll();
    Optional<Reason> findById(long id);
    Optional<Reason> findByText(String name);
    List<Reason> findByValidEquals(boolean valid);
    void delete(Reason Reason);

}