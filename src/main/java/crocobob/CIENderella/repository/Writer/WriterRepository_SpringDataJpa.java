package crocobob.CIENderella.repository.Writer;

import crocobob.CIENderella.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository_SpringDataJpa extends JpaRepository<Writer, Long>, WriterRepository {
}