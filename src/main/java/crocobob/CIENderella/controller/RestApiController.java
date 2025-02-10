package crocobob.CIENderella.controller;

import crocobob.CIENderella.Exception.EntityNotFoundException;
import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class RestApiController {

    private CienderellaService service;

    public RestApiController(CienderellaService service) {
        this.service = service;
    }

    @GetMapping(path = "/form")
    public Form generateNewForm(Model model) {
        // 여기서 form 만들어 갖다주기
        return service.getForm();
    }

    // ====================================================
    // << get >>

    @GetMapping(path = "/content")
    public Content getContent() {
        try {
            return service.getContent();
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @GetMapping(path = "/reasons")
    public List<Reason> getReasonList() {
        // 여기서 reason(사유)들 리스트 갖다주기
        try {
            return service.getAllReasons();
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @GetMapping(path = "/reasons/{id}")
    public Reason getReason(@PathVariable long id) {
        try {
            return service.getReason(id);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @GetMapping(path = "/writers")
    public List<Writer> getWriterList() {
        // 여기서 writers(작성자)들 리스트 갖다주기
        return service.getAllWriters();
    }

    @GetMapping(path = "/writers/{id}")
    public Writer getWriter(@PathVariable long id) {
        // 여기서 writers(작성자)들 리스트 갖다주기
        return service.getWriter(id);
    }

    //=======================================================
    // << create >>

    @PostMapping("/reasons")
    public ResponseEntity<Object> createReason(@RequestBody Reason reason) {
        service.saveReason(reason);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reason.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/writers")
    public ResponseEntity<Object> createWriter(@RequestBody Writer writer) {
        service.saveWriter(writer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(writer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //=======================================================
    // << update >>

    @PatchMapping("/content")
    public void updateContent(@RequestBody Content content) {
        service.patchUpdateContent(content);
    }

    @PatchMapping("/reasons/{id}")
    public void updateReason(@PathVariable long id, @RequestBody Reason reason) {
        service.patchUpdateReason(id, reason);
    }

    @PatchMapping("/writers/{id}")
    public void updateWriter(@PathVariable long id, @RequestBody Writer writer) {
        service.patchUpdateWriter(id, writer);
    }
}
