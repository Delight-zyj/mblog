package com.mblog.controller;

import com.mblog.entry.CreateInfo;
import com.mblog.entry.Result;
import com.mblog.entry.User;
import com.mblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/create")
public class CreateController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Result create(@RequestBody CreateInfo createInfo) {
        log.info("创建用户,{}", createInfo);
        User user =  userService.create(createInfo);
        if (user == null){
            return Result.error("用户名已存在");
        }
        return Result.success(user);
    }


}
