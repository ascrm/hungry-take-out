package com.hungry.service.impl;

import com.hungry.mapper.AdminMapper;
import com.hungry.pojo.entity.Admin;
import com.hungry.pojo.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        Admin admin = adminMapper.getOne(username);

        //如果没查到就抛出异常
        if(Objects.isNull(admin)){
            throw new RuntimeException("用户名或密码错误");
        }
        //查询对象的权限信息

        List<String> permissions = Arrays.asList(admin.getPermission());
        //把数据封装成UserDetails返回
        return new LoginUser(admin,permissions);
    }
}
