package com.taotao.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/")
	public String page2(HttpServletRequest req) {
		
		return "index";
	}
	
}
