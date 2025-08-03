package com.mblog.controller;

import com.mblog.entry.CreateInfo;
import com.mblog.entry.LoginInfo;
import com.mblog.entry.Result;
import com.mblog.entry.User;
import com.mblog.service.UserService;
import com.mblog.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class LoginCreateAndUploadController {

    @Autowired
    private UserService userService;
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;


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

    //    @RsaDecrypt
    @RequestMapping(value = "/create")
    public Result create(@RequestBody CreateInfo createInfo) {
        log.info("创建用户,{}", createInfo);
        User user =  userService.create(createInfo);
        if (user == null){
            return Result.error("用户名已存在");
        }
        return Result.success(user);
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传的文件：{}", file.getOriginalFilename());
        //将文件交给oss存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传成功，文件地址为：{}", url);
        return Result.success(url);
    }
}
