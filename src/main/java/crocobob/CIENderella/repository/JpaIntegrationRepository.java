package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.Content.JpaContentRepository;
import crocobob.CIENderella.repository.Reason.JpaReasonRepository;
import crocobob.CIENderella.repository.Writer.JpaWriterRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaIntegrationRepository implements IntegrationRepository {
    private final JpaContentRepository contentRepo;
    private final JpaReasonRepository reasonRepo;
    private final JpaWriterRepository writerRepo;

    public JpaIntegrationRepository(JpaContentRepository contentRepo, JpaReasonRepository reasonRepo, JpaWriterRepository writerRepo) {
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
    }

    @Override
    public <T> void insert(T entity) {
        switch (entity) {
            case Content content -> contentRepo.insert(content);
            case Reason reason -> reasonRepo.insert(reason);
            case Writer writer -> writerRepo.insert(writer);
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public <T> Optional<T> find(Class<T> c, long id) {
        return switch (c.class){
            case Content.class -> contentRepo.find();
            case Reason.class -> reasonRepo.find(id);
            case Writer.class -> writerRepo.find(id);
            default -> throw new IllegalArgumentException("Invalid class");
        };
    }

    @Override
    public <T> Optional<T> findAny(Class<T> c) {
        return switch(c.class){
            case Reason.class -> reasonRepo.findAny();
            case Writer.class -> writerRepo.findAny();
            default -> throw new IllegalArgumentException("Invalid class");
        };
    }

    @Override
    public <T> void delete(T entity) {

    }

    @Override
    public <T> void updateValid(T entity, boolean isValid) {

    }

}
