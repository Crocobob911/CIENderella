package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return contentRepo.findTopByOrderByIdDesc().orElseThrow(
                () -> new EntityNotFoundException("No content found.")
        );
    }

    @Override
    public List<Reason> findAllReasons() {
        var reasons = reasonRepo.findAll();
        if(reasons.isEmpty()) throw new EntityNotFoundException("There is no Reason in DB.");
        else return reasons;
    }

    @Override
    public List<Writer> findAllWriters() {
        var writers = writerRepo.findAll();
        if(writers.isEmpty()) throw new EntityNotFoundException("There is no Writer in DB.");
        else return writers;
    }


    @Override
    public Reason findReasonById(long id) {
        return reasonRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No Reason with id : " + id + " found.")
        );
    }

    @Override
    public Writer findWriterById(long id) {
        return writerRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No Writer with id : " + id + " found.")
        );
    }


    @Override
    public Reason findAnyReason() {
        var validReasons = reasonRepo.findByValidEquals(true);
        if(validReasons.isEmpty()) throw new EntityNotFoundException("There is no valid Reason in DB.");
        else return validReasons.get(generateRandIndex(validReasons.size()));
    }

    @Override
    public Writer findAnyWriter() {
        var validWriters = writerRepo.findByValidEquals(true);
        if(validWriters.isEmpty()) throw new EntityNotFoundException("There is no valid Writer in DB.");
        else return validWriters.get(generateRandIndex(validWriters.size()));
    }


    @Override
    public Reason findReasonByText(String text) {
        return reasonRepo.findByText(text).orElseThrow(
                () -> new EntityNotFoundException("No Reason with text : " + text + " found.")
        );
    }

    @Override
    public Writer findWriterByText(String text) {
        return writerRepo.findByText(text).orElseThrow(
                () -> new EntityNotFoundException("No Writer with text : " + text + " found.")
        );
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
            case Reason reason -> findReasonById(reason.getId()).setValid(isValid);
            case Writer writer -> findWriterById(writer.getId()).setValid(isValid);
            default -> throw new IllegalArgumentException("Invalid entity");
        }
    }

    @Override
    public void updateContentStatus(boolean onOff) {
        contentRepo.findTopByOrderByIdDesc().orElseThrow(
                () -> new EntityNotFoundException("No content found.")
        ).setStatus(onOff);
    }

    private int generateRandIndex(int num){
        return rand.nextInt(num);
    }

}