package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String pageIndex() {
		return "index";
	}
	
	@RequestMapping("/{path}")
	public String page(@PathVariable String path) {
		return "path";
	}

}
