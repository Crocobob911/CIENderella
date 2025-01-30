package crocobob.CIENderella.repository.Writer;

import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Transactional
@Repository
public class JpaWriterRepository implements WriterRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void insert(Writer writer) {
        em.persist(writer);
    }

    @Override
    public Optional<Writer> find(long id) {
        return Optional.ofNullable(em.find(Writer.class, id));
    }

    @Override
    public Optional<Writer> findAny() {
        return find(generateRandId(writerCount));
    }

    @Override
    public void delete(long id) {
        var entity = em.find(Writer.class, id);
        em.remove(entity);
    }

    @Override
    public void updateValid(long id, boolean isValid) {
        var entity = em.find(Writer.class, id);
        entity.setValid(isValid);
    }

    private long generateRandId(long num){
        return (long) (Math.random()*(int)num+1);
    }

}
