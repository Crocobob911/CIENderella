package crocobob.SISO.Cienderella.Repository.Content;

import crocobob.SISO.Cienderella.Domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository_SpringDataJpa extends JpaRepository<Content, Long>, ContentRepository {
}