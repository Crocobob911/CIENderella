package crocobob.CIENderella.repository.Content;

import crocobob.CIENderella.domain.Content;

import java.util.Optional;

public interface ContentRepository {
    Content save(Content content);
    Optional<Content> findTopByOrderByIdDesc();
    void delete(Content content);
}