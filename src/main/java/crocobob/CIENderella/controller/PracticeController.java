package crocobob.CIENderella.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeController {

    @GetMapping("/practice/hello/{name}")
    public String practice(@PathVariable String name){
        return String.format("Hello %s!", name);
    }
}
