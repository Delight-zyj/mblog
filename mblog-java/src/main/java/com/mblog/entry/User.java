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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAuthorType() {
        return authorType;
    }

    public void setAuthorType(int authorType) {
        this.authorType = authorType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public int getCreateEmployeeNum() {
        return createEmployeeNum;
    }

    public void setCreateEmployeeNum(int createEmployeeNum) {
        this.createEmployeeNum = createEmployeeNum;
    }

    public int getUpdateEmployeeNum() {
        return updateEmployeeNum;
    }

    public void setUpdateEmployeeNum(int updateEmployeeNum) {
        this.updateEmployeeNum = updateEmployeeNum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(Long id, String username, String password, String gender, String email, String phone, String avatar, int authorType, LocalDateTime createTime, LocalDateTime updateTime, int createEmployeeNum, int updateEmployeeNum, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.authorType = authorType;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createEmployeeNum = createEmployeeNum;
        this.updateEmployeeNum = updateEmployeeNum;
        this.age = age;
    }

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
