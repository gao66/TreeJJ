package com.taotao.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	
	public static void main(String[] args) throws IOException {
		
		  ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring/applicationContext-service.xml","spring/applicationContext-dao.xml"});
        context.start();
        System.in.read(); 
	}
}
