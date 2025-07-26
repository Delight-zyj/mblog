package com.mblog.mapper;

import com.mblog.entry.LoginInfo;
import com.mblog.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selecList();


    /**
     * 根据用户名查询用户信息实现登录功能
     * @param username
     * @return
     */
    User selectIdAndPasswordByUsername(String username);

    @Select("select u_image as avatar from t_user where u_username = #{username}")
    String selectAvatarByUsername(String username);

    @Select("select u_username as username from t_user where u_username = #{username}")
    String getUsernameByCreatename(String username);

    void insert(User user);
}
