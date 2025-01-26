package crocobob.CIENderella.controller;

import crocobob.CIENderella.service.DataManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataManagementController {

    private DataManagementService service;

    public DataManagementController(DataManagementService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}
