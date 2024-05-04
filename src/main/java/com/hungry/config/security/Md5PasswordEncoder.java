package com.hungry.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class Md5PasswordEncoder implements PasswordEncoder {
	//自定义密码加密
	@Override
	public String encode(CharSequence rawPassword) {
		//对密码进行 md5 加密
		String md5Password = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
		return md5Password;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}
}
