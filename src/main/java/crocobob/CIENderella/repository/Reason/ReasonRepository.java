package crocobob.CIENderella.repository.Reason;

import crocobob.CIENderella.domain.Reason;

import java.util.Optional;

public interface ReasonRepository {
    void insert(Reason reason);
    Optional<Reason> find(long id);
    void delete(long id);

    void updateValid(long id, boolean isValid);

    Optional<Reason> findAny();
}
