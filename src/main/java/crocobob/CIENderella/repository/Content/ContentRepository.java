package crocobob.CIENderella.repository.Content;

import crocobob.CIENderella.domain.Content;

import java.util.Optional;

public interface ContentRepository {
    void save(Content content);
    Optional<Content> findFirst1ById();
    void delete(Content content);
}
