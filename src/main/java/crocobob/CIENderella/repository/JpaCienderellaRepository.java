package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class JpaCienderellaRepository implements CienderellaRepository{
    private final EntityManager em;

    public JpaCienderellaRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public void addContent(Content content) {
        em.persist(content);
    }

    @Override
    public Optional<Content> getContent() {
        return Optional.ofNullable(
                em.createQuery("select c from Content c", Content.class)
                        .getSingleResult());
    }

    @Override
    public void deleteContent() {

    }


    @Override
    public void addWriter(Writer writer) {
        em.persist(writer);
    }

    @Override
    public Optional<Writer> getAnyWriter() {
        return Optional.empty();
    }

    @Override
    public List<Writer> getAllWriter() {
        return List.of();
    }

    @Override
    public void deleteWriter(long id) {

    }


    @Override
    public void addReason(Reason reason) {
        em.persist(reason);
    }

    @Override
    public Optional<Reason> getAnyReason() {
        return Optional.empty();
    }

    @Override
    public List<Reason> getAllReason() {
        return List.of();
    }

    @Override
    public void deleteReason(long id) {

    }

    @Override
    public void clear() {

    }
}
