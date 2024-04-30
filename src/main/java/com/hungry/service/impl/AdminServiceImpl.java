package com.hungry.service.impl;

import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import com.hungry.mapper.AdminMapper;
import com.hungry.pojo.LoginDto;
import com.hungry.pojo.entity.Admin;
import com.hungry.pojo.entity.LoginUser;
import com.hungry.service.AdminService;
import com.hungry.utils.security.JwtUtil;
import com.hungry.utils.security.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

import static com.hungry.constant.MessageConstant.LOGOUT_SUCCESS;
import static com.hungry.constant.MessageConstant.REGISTER_SUCCESS;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthenticationManager authenticationManager;
    private final RedisCache redisCache;
    private final AdminMapper adminMapper;
    @Override
    public Map<String, String> login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassWord());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //if认证没通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果通过了，使用userid生成一个jwt jwt存入Result返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = String.valueOf(loginUser.getAdmin().getId());
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = Map.of("token", jwt);
        redisCache.setCacheObject("login:"+userid,loginUser);
        return map;
    }

    @Override
    public String logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        int id = loginUser.getAdmin().getId();

        //删除redis中的用户信息
        redisCache.deleteObject("login:"+id);
        return LOGOUT_SUCCESS;
    }

    @Override
    @AutoFill(OperationType.DISH_INSERT)
    public String register(Admin admin) {
        adminMapper.insertOne(admin);
        return REGISTER_SUCCESS;
    }
}
