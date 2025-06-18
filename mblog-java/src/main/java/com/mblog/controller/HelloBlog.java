package com.mblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloBlog {
    @RequestMapping(name = "hello", value = "/")
    public String hello() {
        return "The project start successful!";
    }

    @GetMapping("/eee")
    public String getMain(){
        return "Hello World!";
    }
}
