package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.easyui.EasyUIDataGridResult;
import com.taotao.manager.service.ItemParamService;
import com.taotao.pojo.ItemParamBean;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService; 
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult<ItemParamBean> list(@RequestParam(name="page",defaultValue="1")Integer page, @RequestParam(name="rows",defaultValue="10")Integer rows){
		return itemParamService.list(page,rows);
	}
}
