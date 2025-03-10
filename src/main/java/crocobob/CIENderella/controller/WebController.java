package crocobob.CIENderella.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
