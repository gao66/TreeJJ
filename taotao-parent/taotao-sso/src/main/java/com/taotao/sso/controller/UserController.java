package com.taotao.sso.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@RequestMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		// 权限框架
		Subject suject=SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		suject.login(token);
		return "success";
		
	}
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		return "error";
	}
}
