package com.mblog.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {

    private Long id;
    private String username;
    private String token;
    private String avatar;

}
