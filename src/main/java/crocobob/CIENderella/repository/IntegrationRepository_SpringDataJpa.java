package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
@Transactional
public class IntegrationRepository_SpringDataJpa implements IntegrationRepository {
    private final ContentRepository contentRepo;
    private final ReasonRepository reasonRepo;
    private final WriterRepository writerRepo;

    private Random rand = new Random();

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
    public Content findContent() {
        return contentRepo.findTopByOrderByIdDesc().orElseThrow();
    }

    @Override
    public List<Reason> findAllReasons() {
        return reasonRepo.findAll();
    }

    @Override
    public List<Writer> findAllWriters() {
        return writerRepo.findAll();
    }


    @Override
    public Reason findReasonById(long id) {
        return reasonRepo.findById(id).orElseThrow();
    }

    @Override
    public Writer findWriterById(long id) {
        return writerRepo.findById(id).orElseThrow();
    }


    @Override
    public Reason findAnyReason() {
        var reasons = reasonRepo.findByValidEquals(true);
        return reasons.get(generateRandIndex(reasons.size()));
    }

    @Override
    public Writer findAnyWriter() {
        var writers = writerRepo.findByValidEquals(true);
        return writers.get(generateRandIndex(writers.size()));
    }


    @Override
    public Reason findReasonByText(String text) {
        return reasonRepo.findByText(text).orElseThrow();
    }

    @Override
    public Writer findWriterByText(String text) {
        return writerRepo.findByText(text).orElseThrow();
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
        contentRepo.findTopByOrderByIdDesc().orElseThrow().setStatus(onOff);
    }

    private int generateRandIndex(int num){
        return rand.nextInt(num);
    }

}