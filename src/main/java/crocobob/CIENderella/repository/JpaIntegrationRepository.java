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
    public Optional<Content> findContent(long id) {
        return contentRepo.find();
    }

    @Override
    public Optional<Reason> findReason(long id) {
        return reasonRepo.find(id);
    }

    @Override
    public Optional<Writer> findWriter(long id) {
        return writerRepo.find(id);
    }

    @Override
    public Optional<Reason> findAnyReason() {
        return reasonRepo.findAny();
    }

    @Override
    public Optional<Writer> findAnyWriter() {
        return writerRepo.findAny();
    }


    @Override
    public <T> void delete(T entity) {
        switch (entity) {
            case Content content -> contentRepo.delete();
            case Reason reason -> reasonRepo.delete(reason.getId());
            case Writer writer -> writerRepo.delete(writer.getId());
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public <T> void updateValid(T entity, boolean isValid) {
        switch (entity) {
            case Reason reason -> reasonRepo.updateValid(reason.getId(), isValid);
            case Writer writer -> writerRepo.updateValid(writer.getId(), isValid);
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public void updateContentStatus(boolean onOff) {
        contentRepo.updateStatus(onOff);
    }
}
