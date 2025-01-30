package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntegrationRepository_SpringDataJpa implements IntegrationRepository {
    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;

    public IntegrationRepository_SpringDataJpa(ContentRepository contentRepo, ReasonRepository reasonRepo, WriterRepository writerRepo) {
        this.contentRepo = contentRepo;
        this.reasonRepo = reasonRepo;
        this.writerRepo = writerRepo;
    }

    @Override
    public <T> void save(T entity) {
        switch (entity) {
            case Content content -> contentRepo.save(content);
            case Reason reason -> reasonRepo.save(reason);
            case Writer writer -> writerRepo.save(writer);
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public Optional<Content> findContent(long id) {
        return contentRepo.findFirst1ById();
    }

    @Override
    public Optional<Reason> findReasonById(long id) {
        return reasonRepo.findById(id);
    }

    @Override
    public Optional<Writer> findWriterById(long id) {
        return writerRepo.findById(id);
    }

    @Override
    public Optional<Reason> findAnyReason() {
        return reasonRepo.findByValidEquals(true).stream().findAny();
    }

    @Override
    public Optional<Writer> findAnyWriter() {
        return writerRepo.findByValidEquals(true).stream().findAny();
    }


    @Override
    public <T> void delete(T entity) {
        switch (entity) {
            case Content content -> contentRepo.delete(content);
            case Reason reason -> reasonRepo.delete(reason);
            case Writer writer -> writerRepo.delete(writer);
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public <T> void updateValid(T entity, boolean isValid) {
        switch (entity) {
            case Reason reason -> reasonRepo.findById(reason.getId()).orElseThrow().setValid(isValid);
            case Writer writer -> writerRepo.findById(writer.getId()).orElseThrow().setValid(isValid);
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public void updateContentStatus(boolean onOff) {
        contentRepo.findFirst1ById().orElseThrow().setStatus(onOff);
    }

    private long generateRandomId(int num){
        return (long) (Math.random()*num);
    }
}
