package com.mblog.controller;

import com.mblog.annotation.RsaDecrypt;
import com.mblog.service.HelloBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloBlog {
    @Autowired
    private HelloBlogService helloBlogService;

    @RsaDecrypt
    @RequestMapping(name = "hello", value = "/")
    public String hello() {
        return "The project start successful!";
    }

    @RsaDecrypt
    @PostMapping("/eee")
    public String getMain(){
        return helloBlogService.test();
    }
}
