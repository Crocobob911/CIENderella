package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class WebController {

    private CienderellaService service;

    public WebController(CienderellaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/get-form")
    @ResponseBody
    public Form generateNewForm(Model model) {
        // 여기서 form 만들어 갖다주기
        return service.getForm().orElseThrow();
    }

    @GetMapping("/content")
    @ResponseBody
    public Content getContent(Model model) {
        return service.getContent().orElseThrow();
    }

    @GetMapping("/reasons")
    @ResponseBody
    public List<Reason> getReasons(Model model) {
        // 여기서 reason(사유)들 리스트 갖다주기
        return service.getAllReasons();
    }

    @GetMapping("/writers")
    @ResponseBody
    public List<Writer> getWriters(Model model) {
        // 여기서 writers(작성자)들 리스트 갖다주기
        return service.getAllWriters();
    }

    // POST 들은 어떻게 구현하지?
}
