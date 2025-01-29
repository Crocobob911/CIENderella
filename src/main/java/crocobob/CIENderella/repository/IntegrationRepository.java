package crocobob.CIENderella.repository;

import java.util.Optional;

public interface IntegrationRepository {
    public <T> void insert(T entity);
    public <T> Optional<T> find(Class<T> c, long id);
    public <T> Optional<T> findAny(Class<T>c);
    public <T> void delete(T entity);
    public <T> void updateValid(T entity, boolean isValid);
}
