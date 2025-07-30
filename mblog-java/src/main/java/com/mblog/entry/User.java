package com.mblog.entry;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;

@Data
@TableName("t_user")
@Accessors(chain = true)
public class User {
    /**
     * 用户类，用于表示系统中的用户信息
     */
    @TableId(value = "u_userId", type = IdType.AUTO)
    private Long userId; // 用户唯一标识符

    @TableField(value = "u_username")
    private String username; // 用户名
    @TableField(value = "u_password")
    private String password; // 用户密码，用于账户安全
    @TableField(value = "i_gender_code")
    private int gender; // 用户性别
    @TableField(value = "u_email")
    private String email; // 用户邮箱，用于账户验证和信息推送
    @TableField(value = "u_phone")
    private String phone; // 用户联系电话
    @TableField(value = "u_image")
    private String avatar; // 用户头像URL
    @TableField(value = "i_author_type")
    private int authorType; // 作者类型，表示用户的角色或权限级别
    @TableField(value = "d_create_time")
    private LocalDateTime createTime; // 用户创建时间
    @TableField(value = "d_update_time")
    private LocalDateTime updateTime; // 用户信息最后更新时间
    @TableField(value = "u_create_employee_num")
    private int createEmployeeNum; // 创建该用户时的员工编号
    @TableField(value = "u_update_employee_num")
    private int updateEmployeeNum; // 最后更新该用户信息时的员工编号
    @TableField(value = "u_age")
    private Integer age; // 用户年龄


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
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

}
