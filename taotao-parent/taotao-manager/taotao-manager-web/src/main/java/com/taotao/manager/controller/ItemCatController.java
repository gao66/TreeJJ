package com.taotao.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.easyui.EasyUITreeNode;
import com.taotao.manager.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService; 
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> list(@RequestParam(defaultValue="0") Long id) throws Exception {
	 List<EasyUITreeNode> list = itemCatService.list(id);
	 System.out.println("list"+list);
	 return list;
	}
	
	
}
