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
        em.persist(content);
    }

    @Override
    public Optional<Content> find() {
        return Optional.ofNullable(
                em.find(Content.class, 1L)
        );
    }

    @Override
    public void delete() {
        try{
        var content = em.find(Content.class, 1);
        em.remove(content);
        }catch(Exception e){
            return;
        }
    }

    @Override
    public void updateStatus(boolean status) {
        var entity = em.find(Content.class, 1L);
        entity.setStatus(status);
    }
}
