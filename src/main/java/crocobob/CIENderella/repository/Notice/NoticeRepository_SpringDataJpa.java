package crocobob.CIENderella.repository.Notice;

import crocobob.CIENderella.domain.Kiosk.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository_SpringDataJpa extends JpaRepository<Notice, Long>, NoticeRepository {
}
