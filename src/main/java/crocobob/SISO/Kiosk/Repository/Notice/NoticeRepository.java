package crocobob.SISO.Kiosk.Repository.Notice;

import crocobob.SISO.Kiosk.Domain.Notice.Notice;

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
