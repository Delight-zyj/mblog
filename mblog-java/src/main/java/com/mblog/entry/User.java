package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;

@Data
@TableName("user")
@Accessors(chain = true)
public class User {
    /**
     * 用户类，用于表示系统中的用户信息
     */
    private Long id; // 用户唯一标识符
    private String username; // 用户名
    private String password; // 用户密码，用于账户安全
    private String gender; // 用户性别
    private String email; // 用户邮箱，用于账户验证和信息推送
    private String phone; // 用户联系电话
    private String avatar; // 用户头像URL
    private int authorType; // 作者类型，表示用户的角色或权限级别
    private LocalDateTime createTime; // 用户创建时间
    private LocalDateTime updateTime; // 用户信息最后更新时间
    private int createEmployeeNum; // 创建该用户时的员工编号
    private int updateEmployeeNum; // 最后更新该用户信息时的员工编号
    private Integer age; // 用户年龄


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", authorType=" + authorType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createEmployeeNum='" + createEmployeeNum + '\'' +
                ", updateEmployeeNum='" + updateEmployeeNum + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", email='" + email + '\'' +
//
//                '}';
//    }
}
