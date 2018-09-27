package org.lq.main;

import java.util.Scanner;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Demo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
	
		//1.加载配置文件
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2.创建一个安全管理器对象
		SecurityManager securityManager = factory.getInstance();
		
		// 3.将安全管理器对象设置进某一个环境SecurityUtils，SecurityUtils就代表整个安全框架。
		SecurityUtils.setSecurityManager(securityManager);
		
		
		while(true) {
			String username = sc.next();
			String password = sc.next();
		//4. 获取一个用户
		Subject currentUser=SecurityUtils.getSubject();
		//本对象就是携带了用户输入的用户名和密码。
		AuthenticationToken token=new UsernamePasswordToken(username, password);
		
		//判断用户是否登陆
		if(currentUser.isAuthenticated()==false) {
			//执行登陆，如果不抛异常代表登陆成功。
			try {
			
			currentUser.login(token);
			System.out.println("登陆成功");
		}catch (AuthenticationException e) {
			System.out.println("登陆失败");
		}
			}else {
				System.out.println("已登录，勿重复");
			}
		
	}
	}
}