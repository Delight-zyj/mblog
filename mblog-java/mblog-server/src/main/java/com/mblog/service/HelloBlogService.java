package com.mblog.service;

import com.mblog.entry.User;
import com.mblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloBlogService {
    @Autowired
    private UserMapper userMapper;


    public String test() {
        List<User> userList = userMapper.selecList();
        return userList.toString();
    }
}
