package com.hungry.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import com.hungry.config.security.Md5PasswordEncoder;
import com.hungry.mapper.AdminMapper;
import com.hungry.pojo.DTO.LoginDto;
import com.hungry.pojo.DTO.RegisterDto;
import com.hungry.pojo.Result;
import com.hungry.pojo.entity.Admin;
import com.hungry.pojo.entity.LoginUser;
import com.hungry.pojo.entity.Mail;
import com.hungry.service.AdminService;
import com.hungry.utils.MailUtil;
import com.hungry.utils.security.JwtUtil;
import com.hungry.utils.security.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.hungry.constant.MessageConstant.LOGOUT_SUCCESS;
import static com.hungry.constant.MessageConstant.REGISTER_SUCCESS;
import static com.hungry.constant.RedisKeyConstant.ADMIN_REGISTER_CODE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AuthenticationManager authenticationManager;
    private final RedisCache redisCache;
    private final AdminMapper adminMapper;
    private final JavaMailSender javaMailSender;

    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public Map<String, String> login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //if认证没通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //如果通过了，使用userid生成一个jwt jwt存入Result返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = String.valueOf(loginUser.getAdmin().getId());
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = Map.of("Authorization", jwt);
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
    public String register(RegisterDto registerDto) {
        String redisCode = stringRedisTemplate.opsForValue().get("admin:register:" + registerDto.getMail());
        if(registerDto.getCode()==null){
            return "验证码已过期";
        }
        if(!registerDto.getCode().equals(redisCode)){
            return "验证码错误";
        }
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encode = md5PasswordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(encode);
        Admin admin = new Admin();
        BeanUtil.copyProperties(registerDto,admin,true);
        adminMapper.insertOne(admin);
        return REGISTER_SUCCESS;
    }

    @Override
    public Result<String> sendMail(Admin admin) {
        String code = RandomUtil.randomNumbers(6);
        Mail mail = new Mail();
        mail.setSender("2339621373@qq.com");
        mail.setSubject("hungry-take-out注册通知");
        mail.setRecipient(admin.getMail());
        mail.setContent("您的验证码为："+code+",有效期两分钟,请注意时间！");

        MailUtil.sendSimpleMail(javaMailSender,mail);
        stringRedisTemplate.opsForValue().set("admin:register:"+admin.getMail(),code,2L, TimeUnit.MINUTES);
        return Result.success(code);
    }
}
