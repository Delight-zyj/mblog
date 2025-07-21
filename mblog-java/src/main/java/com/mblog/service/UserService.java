package com.mblog.service;

import com.mblog.entry.LoginInfo;
import com.mblog.entry.User;

public interface UserService {

    LoginInfo selectUsernameAndPasswordById(User user);
}
