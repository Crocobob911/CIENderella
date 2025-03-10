package crocobob.CIENderella.repository.Notice;

import crocobob.CIENderella.domain.Kiosk.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository {
    Notice save(Notice notice);
    Optional<Notice> findById(long id);
    Optional<Notice> findByNotice(String notice);
    List<Notice> findTop3ByOrderByIdDesc();
    void delete(Notice notice);
    List<Notice> findAll();
}
