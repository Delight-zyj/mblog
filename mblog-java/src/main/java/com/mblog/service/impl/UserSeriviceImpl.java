package com.mblog.service.impl;

import com.mblog.entry.Result;
import com.mblog.utils.JwtUtils;
import com.mblog.entry.CreateInfo;
import com.mblog.entry.LoginInfo;
import com.mblog.entry.User;
import com.mblog.mapper.UserMapper;
import com.mblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
public class UserSeriviceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginInfo login(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        // 对密码进行MD5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 1、调用Mapper查询数据库中加密过的密码和id
        User u = userMapper.selectIdAndPasswordByUsername(username);

        // 2、判断是否存在这个用户
        if (u == null) {
            // 全局异常处理：用户不存在
            log.info("用户不存在");
            return new LoginInfo(-1L, "用户不存在", null,null);
        }

        // 获取数据库中的密码
        String dbpassword = u.getPassword();

        // 判断用户名和密码匹配是否一致
        if (!dbpassword.equals(password)) {
            // 全局异常处理：用户名或密码错误
            log.info("用户名或密码错误");
            return new LoginInfo(-2L, "用户名或密码错误", null,null);
        }

        // 获取用户头像
        u.setAvatar(userMapper.selectAvatarByUsername(username));

        // 3、匹配一致则生成token登录
        if (dbpassword.equals(password)) {
            // 生成JWT令牌
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("UserId", u.getUserId());
            claims.put("username", u.getUsername());
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(u.getUserId(), u.getUsername(), token, u.getAvatar());
        }

        // 不存在返回null
        return null;
    }

    @Override
    public User create(CreateInfo createInfo) {
        // 1、判断创建用户名是否已经存在
        String createname = createInfo.getUsername();
        String username = userMapper.getUsernameByCreatename(createname);
        if(username .equals(createname)){
            log.info("用户名已存在");
            return null;
        }

        User user = new User();
        String password = DigestUtils.md5DigestAsHex(createInfo.getPassword1().getBytes());

        user.setUsername(createname);
        user.setPassword(password);
        user.setEmail(createInfo.getEmail());
        user.setGender(3);
        user.setAuthorType(1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.insert(user);
        return user;
    }
}
