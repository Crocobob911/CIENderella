package crocobob.SISO.Kiosk.Repository.Notice;

import crocobob.SISO.Kiosk.Domain.Notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository_SpringDataJpa extends JpaRepository<Notice, Long>, NoticeRepository {
}
