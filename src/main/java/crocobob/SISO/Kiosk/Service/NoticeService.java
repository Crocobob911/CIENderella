package crocobob.SISO.Kiosk.Service;

import crocobob.SISO.Exception.DBEntityNotFoundException;
import crocobob.SISO.Kiosk.Domain.Notice.Notice;
import crocobob.SISO.Kiosk.Domain.Notice.NoticeDTO;
import crocobob.SISO.Kiosk.Repository.Notice.NoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NoticeService {

    private NoticeRepository repo;

    public NoticeService(NoticeRepository repository) {
        this.repo = repository;
    }

    public Notice save(NoticeDTO dto) {
        String[] dtoStringParts = dto.getNotice().split(" \\| ");

        var notice = repo.findByNotice(dtoStringParts[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return notice.orElseGet(() -> repo.save(new Notice(dtoStringParts[0], LocalDate.parse(dtoStringParts[1], formatter))));
    }

    public List<Notice> getNotices(){
        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);

        List<Notice> notices = repo.findTop3ByOrderByDateDesc();
        notices.removeIf(notice -> notice.getDate().isBefore(twoMonthsAgo));

        if(notices.isEmpty())
            notices.add(new Notice(9999,"지금은 공지가 없어요! 새로운 공지가 생기면 바로 알려드릴게요 :D", LocalDate.now()));

        return notices;
    }

    public List<Notice> getAllNotices(){
        return repo.findAll();
    }

    public void deleteNotice(long id) {
        repo.delete(repo.findById(id).orElseThrow(() -> new DBEntityNotFoundException("<< No Notice found >>")));
    }
}
