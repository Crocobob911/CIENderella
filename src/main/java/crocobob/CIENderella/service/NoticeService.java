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
        return repo.save(new Notice(dto.getNotice(), LocalDate.now()));
    }

    public List<Notice> getNotices(){
        return repo.findTop3ByOrderByIdDesc();
    }

    public List<Notice> getAllNotices(){
        return repo.findAll();
    }

    public void delete(Notice notice) {}
}
