package com.hungry.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author:ascrm
 * @Date:2024/5/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String username;
    private String password;
    private String mail;
    private String permission;
    private String code;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
