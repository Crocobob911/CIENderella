package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;

import java.util.Optional;

public interface IntegrationRepository {
    public <T> void insert(T entity);

    public Optional<Content> findContent(long id);
    public Optional<Reason> findReason(long id);
    public Optional<Writer> findWriter(long id);

    public Optional<Reason> findAnyReason();
    public Optional<Writer> findAnyWriter();

    public <T> void delete(T entity);
    public void updateContentStatus(boolean onOff);
    public <T> void updateValid(T entity, boolean isValid);
}
