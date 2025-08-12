package com.mblog.controller;

import com.mblog.utils.AliyunOSSOperator;
import com.mblog.entry.Result;
import com.mblog.entry.User;
import com.mblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/userinfo/{id}","/user-1/{id}","/user-2/{id}","/home{id}","/blog/{id}","/author/{id}",})
    public Result getUserinfoById(@PathVariable Long id) {
        log.info("查询用户id:{}",id);
        User user = userService.selectIdUserinfoById(id);
        return Result.success(user);
    }


    @PutMapping({"/userinfo","/user-1","/user-2","/user-3"})
    public Result updateUserinfo1(@RequestBody User user) {
        userService.updateUserinfo(user);
        return Result.success();
    }




}
