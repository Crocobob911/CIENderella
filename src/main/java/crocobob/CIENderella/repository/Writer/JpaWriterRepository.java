package crocobob.CIENderella.repository.Writer;

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
        return Optional.empty();
    }

    @Override
    public Optional<Writer> findAny() {
        return Optional.empty();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void updateValid(long id, boolean isValid) {

    }

}
