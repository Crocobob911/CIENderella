package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @GetMapping("/new-form")
    @ResponseBody
    public String generateNewForm(Model model) {
        // 여기서 form 만들어 갖다주기
        return service.getForm().orElseThrow().toString();
    }

    @GetMapping("/reasons")
    public List<String> reasons(Model model) {
        // 여기서 reason(사유)들 리스트 갖다주기
        return null;
    }

    @GetMapping("/writers")
    public String writers(Model model) {
        // 여기서 writers(작성자)들 리스트 갖다주기
        return null;
    }

    // POST 들은 어떻게 구현하지?
}
