package crocobob.SISO.Cienderella.Service;

import crocobob.SISO.Cienderella.Domain.Content;
import crocobob.SISO.Cienderella.Domain.ContentDTO;
import crocobob.SISO.Cienderella.Repository.Content.ContentRepository;
import crocobob.SISO.Exception.DBEntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    private final ContentRepository contentRepo;

    public ContentService(ContentRepository contentRepo) {
        this.contentRepo = contentRepo;
    }

    public Content getContent() {
        return contentRepo.findTopByOrderByIdDesc()
                .orElseThrow(() -> new DBEntityNotFoundException("<< Content not found >>"));
    }

    public void patchUpdateContent(ContentDTO newContent) {
        Content oldContent = getContent();

        if(newContent.getStartTime() != null) oldContent.setStartTime(newContent.getStartTime());
        if(newContent.getEndTime() != null) oldContent.setEndTime(newContent.getEndTime());
        if(newContent.getPassword() != null) oldContent.setPassword(newContent.getPassword());
        if(newContent.getStatus() != null) oldContent.setStatus(newContent.getStatus());
        if(newContent.getTitle() != null) oldContent.setTitle(newContent.getTitle());
        if(newContent.getText() != null) oldContent.setText(newContent.getText());

        contentRepo.save(oldContent);
    }
}
