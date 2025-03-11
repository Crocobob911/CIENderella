package crocobob.SISO.Cienderella.Repository.Content;

import crocobob.SISO.Cienderella.Domain.Content;

import java.util.Optional;

public interface ContentRepository {
    Content save(Content content);
    Optional<Content> findTopByOrderByIdDesc();
    void delete(Content content);
}