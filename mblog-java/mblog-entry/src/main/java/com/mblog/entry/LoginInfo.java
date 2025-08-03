package com.mblog.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {

    private Long id;
    private String username;
    private String token;

//    private String avatar;
//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;
//    private int authorType;
//    private int age;
//    private String email;
//    private String phone;
//    private int gender;


}
