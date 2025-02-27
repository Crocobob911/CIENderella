package crocobob.CIENderella.controller;


import crocobob.CIENderella.domain.Notice;
import crocobob.CIENderella.domain.NoticeDTO;
import crocobob.CIENderella.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class NoticeController {
    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    @GetMapping(path="/notices")
    public List<Notice> getNotice() {
        return service.getNotices();
    }

    @PostMapping(path="/notices")
    public ResponseEntity createNotice(@RequestBody NoticeDTO dto) {
        var notice = service.save(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(notice.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
