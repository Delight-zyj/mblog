package com.mblog.controller;

import com.mblog.entry.LoginInfo;
import com.mblog.entry.Result;
import com.mblog.entry.User;
import com.mblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
//@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userinfo/{id}")
    public Result getUserinfoById(@PathVariable Long id) {
        User user = userService.selectIdUserinfoById(id);
        return Result.success(user);
    }
    @GetMapping("/user-1/{id}")
    public Result getUserinfoById1(@PathVariable Long id) {
        User user = userService.selectIdUserinfoById(id);
        return Result.success(user);
    }
    @GetMapping("/user-2/{id}")
    public Result getUserinfoById2(@PathVariable Long id) {
        User user = userService.selectIdUserinfoById(id);
        return Result.success(user);
    }
}
