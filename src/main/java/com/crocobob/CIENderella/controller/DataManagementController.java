package com.crocobob.CIENderella.controller;

import com.crocobob.CIENderella.service.DataManagementService;
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
    @ResponseBody
    public String home() {
        return "Welcome! This is home!";
    }
}
