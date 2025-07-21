package com.mblog.service.impl;

import com.mblog.Utils.JwtUtils;
import com.mblog.entry.LoginInfo;
import com.mblog.entry.User;
import com.mblog.mapper.UserMapper;
import com.mblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserSeriviceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginInfo selectUsernameAndPasswordById(User user) {
        // 1、调用Mapper查询
        LoginInfo loginInfo = userMapper.selectUsernameAndPasswordById(user);
        // 2、判断是否存在这个用户
        if (loginInfo != null){
            // 生成JWT令牌
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", loginInfo.getId());
            claims.put("username", loginInfo.getUsername());
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(loginInfo.getId(), loginInfo.getUsername(), token);
        }
        // 3、不存在返回null
        return null;
    }
}
