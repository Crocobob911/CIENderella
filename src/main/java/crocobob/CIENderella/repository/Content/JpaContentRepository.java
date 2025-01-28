package crocobob.CIENderella.repository.Content;

import crocobob.CIENderella.domain.Content;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public class JpaContentRepository implements ContentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Content content) {
        delete();
        em.merge(content);
    }

    @Override
    public Optional<Content> find() {
        return Optional.ofNullable(
                em.find(Content.class, 1L)
        );
    }

    @Override
    public void delete() {
        Content content = em.find(Content.class, 1);
        em.remove(content);
    }

    public long generateRandId(long num){
        return (long) (Math.random()*(int)num+1);
    }
}
