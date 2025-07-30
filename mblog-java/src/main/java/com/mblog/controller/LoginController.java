package com.mblog.controller;

import com.mblog.annotation.RsaDecrypt;
import com.mblog.entry.LoginInfo;
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
public class LoginController {

    @Autowired
    private UserService userService;

//    @RsaDecrypt
    @RequestMapping(value = "/login")
    public Result login(@RequestBody User user) {
        log.info("登录:{}", user);
        LoginInfo loginInfo = userService.login(user);
        if (loginInfo.getId().equals(-1L)){
            return Result.error("用户不存在");
        }
        if (loginInfo.getId().equals(-2L)){
            return Result.error("用户名或密码错误");
        }
        return Result.success(loginInfo);
    }
}
