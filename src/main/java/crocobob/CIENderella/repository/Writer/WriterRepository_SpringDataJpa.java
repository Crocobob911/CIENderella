package crocobob.CIENderella.repository.Writer;

import crocobob.CIENderella.domain.cienderella.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository_SpringDataJpa extends JpaRepository<Writer, Long>, WriterRepository {
}