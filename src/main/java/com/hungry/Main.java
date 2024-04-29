package com.hungry;

import com.hungry.config.security.Md5PasswordEncoder;

public class Main {
    public static void main(String[] args) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encode = md5PasswordEncoder.encode("123456");
        System.out.println(encode);
    }
}
