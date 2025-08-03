package com.mblog.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInfo {

    private long id;
    private String username;
    private String password1;// 密码
    private String password2;// 确认密码
    private String email;
}
