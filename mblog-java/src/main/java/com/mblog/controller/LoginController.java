package com.mblog.controller;

import com.mblog.entry.LoginInfo;
import com.mblog.entry.Result;
import com.mblog.entry.User;
import com.mblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping
    public Result login(@RequestBody User user) {
        log.info("登录:{}", user);
        LoginInfo loginInfo = userService.login(user);
//        if (loginInfo != null) {
//            return Result.success(loginInfo);
//        }
//        return Result.error("用户名或密码错误！");

        return Result.success(loginInfo);
    }
}
