package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    private CienderellaService service;

    public RestApiController(CienderellaService service) {
        this.service = service;
    }

    @GetMapping("/api/get-form")
    @ResponseBody
    public Form generateNewForm(Model model) {
        // 여기서 form 만들어 갖다주기
        return service.getForm();
    }

    @GetMapping("/api/content")
    @ResponseBody
    public Content getContent(Model model) {
        return service.getContent();
    }

    @GetMapping("/api/reasons")
    @ResponseBody
    public List<Reason> getReasons(Model model) {
        // 여기서 reason(사유)들 리스트 갖다주기
        return service.getAllReasons();
    }

    @GetMapping("/api/writers")
    @ResponseBody
    public List<Writer> getWriters(Model model) {
        // 여기서 writers(작성자)들 리스트 갖다주기
        return service.getAllWriters();
    }


    @PostMapping("/api/content")
    public String saveNewContent(Model model, Content content) {
        return null;
    }

    @PostMapping("/api/reason")
    public String saveNewReason(Model model, Reason reason) {
        return null;
    }

    @PostMapping("/api/writer")
    public String saveNewWriter(Model model, Writer writer) {
        return null;
    }
}
