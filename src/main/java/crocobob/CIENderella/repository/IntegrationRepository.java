package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;

import java.util.List;
import java.util.Optional;

public interface IntegrationRepository {

    public <T> void save(T entity);


    public Content findContent();

    List<Reason> findAllReasons();

    List<Writer> findAllWriters();

    public Reason findAnyReason();

    public Writer findAnyWriter();


    public <T> void delete(T entity);

    public void updateContentStatus(boolean onOff);

    public <T> void updateValid(T entity, boolean isValid);
}