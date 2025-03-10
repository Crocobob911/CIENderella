package crocobob.CIENderella.repository.Content;

import crocobob.CIENderella.domain.cienderella.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository_SpringDataJpa extends JpaRepository<Content, Long>, ContentRepository {
}