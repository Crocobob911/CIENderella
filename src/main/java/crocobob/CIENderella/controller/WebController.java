package crocobob.CIENderella.controller;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Form;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import crocobob.CIENderella.service.CienderellaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @RequestMapping("/")
    public String home(Model model) {
        return "home";
        // 리다이렉션은 못 시키나?
        // 그냥 localhost:8080으로 들어온 사람을
        // localhost:8080/home 으로 리다이렉트
    }
}
