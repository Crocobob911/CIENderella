package crocobob.CIENderella.repository.Content;

import crocobob.CIENderella.domain.Content;

import java.util.Optional;

public interface ContentRepository {
    void insert(Content content);
    Optional<Content> find();
    void delete();
}
