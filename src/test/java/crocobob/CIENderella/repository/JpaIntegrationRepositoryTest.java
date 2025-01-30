package crocobob.CIENderella.repository;

import crocobob.CIENderella.repository.Content.JpaContentRepository;
import crocobob.CIENderella.repository.Reason.JpaReasonRepository;
import crocobob.CIENderella.repository.Writer.JpaWriterRepository;

import static org.junit.jupiter.api.Assertions.*;

class JpaIntegrationRepositoryTest {

    JpaIntegrationRepository repo = new JpaIntegrationRepository(
            new JpaContentRepository(),
            new JpaReasonRepository(),
            new JpaWriterRepository()
    );

}