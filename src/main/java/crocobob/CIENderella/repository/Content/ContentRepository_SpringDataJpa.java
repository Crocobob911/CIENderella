package crocobob.CIENderella.repository.Content;

import crocobob.CIENderella.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContentRepository_SpringDataJpa extends JpaRepository<Content, Long>, ContentRepository {
}