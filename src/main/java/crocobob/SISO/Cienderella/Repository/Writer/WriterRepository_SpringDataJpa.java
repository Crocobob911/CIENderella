package crocobob.SISO.Cienderella.Repository.Writer;

import crocobob.SISO.Cienderella.Domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository_SpringDataJpa extends JpaRepository<Writer, Long>, WriterRepository {
}