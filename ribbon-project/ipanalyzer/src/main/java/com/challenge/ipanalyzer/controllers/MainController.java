package com.challenge.ipanalyzer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @GetMapping("/")
    public String health() {

        return "I am Ok";
    }

    @ResponseBody
    @GetMapping("/backend")
    public String hello() {

        return "Hello Controller";
    }

}
