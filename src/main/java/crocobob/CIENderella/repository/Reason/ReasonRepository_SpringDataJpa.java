package crocobob.CIENderella.repository.Reason;

import crocobob.CIENderella.domain.cienderella.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository_SpringDataJpa extends JpaRepository<Reason, Long>, ReasonRepository{
}