package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.repository.Content.ContentRepository;
import crocobob.CIENderella.repository.Reason.ReasonRepository;
import crocobob.CIENderella.repository.Writer.WriterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationRepository_SpringDataJpaTest {

    private ContentRepository contentRepo;
    private ReasonRepository reasonRepo;
    private WriterRepository writerRepo;

    private IntegrationRepository integrationRepo;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
}