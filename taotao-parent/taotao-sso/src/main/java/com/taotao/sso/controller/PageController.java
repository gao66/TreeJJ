package com.taotao.sso.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String page(@PathVariable String page) {
		return "index";
	}
	
	@RequestMapping("/{path}")
	public String page2(HttpServletRequest req) {
		
		return "path";
	}
	
}
