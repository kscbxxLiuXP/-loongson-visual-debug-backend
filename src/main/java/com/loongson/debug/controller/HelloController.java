package com.loongson.debug.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, welcome!\n If you see this line of word, the service is on running ";
    }
}
