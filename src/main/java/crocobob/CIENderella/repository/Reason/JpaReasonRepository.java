package crocobob.CIENderella.repository.Reason;

import crocobob.CIENderella.domain.Reason;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public class JpaReasonRepository implements ReasonRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void insert(Reason reason) {
        em.persist(reason);
    }

    @Override
    public Optional<Reason> find(long id) {
        return Optional.ofNullable(em.find(Reason.class, id));
    }

    @Override
    public Optional<Reason> findAny() {
        return find(generateRandId(reasonCount));
    }

    @Override
    public void delete(long id) {
        var entity = em.find(Reason.class, id);
        em.remove(entity);
    }

    @Override
    public void updateValid(long id, boolean isValid) {
        var entity = em.find(Reason.class, id);
        entity.setValid(isValid);
    }

    private long generateRandId(long num){
        return (long) (Math.random()*(int)num+1);
    }
}
