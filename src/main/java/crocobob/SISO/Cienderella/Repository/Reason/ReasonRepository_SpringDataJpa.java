package crocobob.SISO.Cienderella.Repository.Reason;

import crocobob.SISO.Cienderella.Domain.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository_SpringDataJpa extends JpaRepository<Reason, Long>, ReasonRepository{
}