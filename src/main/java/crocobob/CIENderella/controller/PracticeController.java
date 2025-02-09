package crocobob.CIENderella.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PracticeController {

    @GetMapping("/practice/hello/{name}")
    public String practice(@PathVariable String name){
        return String.format("Hello %s!", name);
    }
}
