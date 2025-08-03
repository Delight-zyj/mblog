package com.mblog.service;

import com.mblog.entry.CreateInfo;
import com.mblog.entry.LoginInfo;
import com.mblog.entry.User;

public interface UserService {

    LoginInfo login(User user);

    User create(CreateInfo createInfo);

    void updateUserinfo(User user);

    User selectIdUserinfoById(Long id);
}
