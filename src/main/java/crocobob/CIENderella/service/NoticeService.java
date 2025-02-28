package crocobob.CIENderella.service;

import crocobob.CIENderella.domain.Notice;
import crocobob.CIENderella.domain.NoticeDTO;
import crocobob.CIENderella.repository.Notice.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoticeService {

    private NoticeRepository repo;

    public NoticeService(NoticeRepository repository) {
        this.repo = repository;
    }

    public Notice save(NoticeDTO dto) {
        var notice = repo.findByNotice(dto.getNotice());

        return notice.orElseGet(() -> repo.save(new Notice()));
    }

    public List<Notice> getNotices(){
        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);

        List<Notice> notices = repo.findTop3ByOrderByIdDesc();
        notices.removeIf(notice -> notice.getDate().isBefore(twoMonthsAgo));
        return notices;
    }

    public List<Notice> getAllNotices(){
        return repo.findAll();
    }

    public void delete(Notice notice) {}
}
