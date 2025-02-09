package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    private CienderellaService service;

    public RestApiController(CienderellaService service) {
        this.service = service;
    }

    @GetMapping(path = "/get-form")
    public Form generateNewForm(Model model) {
        // 여기서 form 만들어 갖다주기
        return service.getForm();
    }

    @GetMapping(path = "/content")
    public Content getContent() {
        return service.getContent();
    }

    @GetMapping(path = "/reasons")
    public List<Reason> getReasons() {
        // 여기서 reason(사유)들 리스트 갖다주기
        return service.getAllReasons();
    }

    @GetMapping(path = "/writers")
    public List<Writer> getWriters() {
        // 여기서 writers(작성자)들 리스트 갖다주기
        return service.getAllWriters();
    }



    @PostMapping("/content")
    public void createContent(@RequestBody Content content) {
        service.saveContent(content);
    }

    @PostMapping("/reasons")
    public void createReason(@RequestBody Reason reason) {
        service.saveReason(reason);
    }

    @PostMapping("/writers")
    public void createWriter(@RequestBody Writer writer) {
        service.saveWriter(writer);
    }


    @PatchMapping("/content")
    public void updateContent(@RequestBody Content content) {

    }

    @PatchMapping("/reasons")
    public void updateReason(@RequestBody Reason reason) {}

    @PatchMapping("/writers")
    public void updateWriter(@RequestBody Writer writer) {}
}
