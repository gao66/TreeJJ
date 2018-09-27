package com.taotao.manager.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.easyui.EasyUIDataGridResult;
import com.taotao.exception.ItemListException;
import com.taotao.exception.ItemSaveException;
import com.taotao.json.result.ItemSaveResult;
import com.taotao.manager.service.ItemService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.ui.form.bean.ItemFormBean;
/*
	cid=[3]
title=[aaaaaa]
sellPoint=[aaaaaa]
priceView=[12.00]
price=[1200]
num=[23]
barcode=[1122111]
image=[http://localhost/download/upload/image/2018-09-15-06-44-51-71020c7b-e629-4635-90f3-0bd7eeb383e7.jpg]
desc=
itemParams=[]

* */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService; 
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult<TbItem> list(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="30")Integer rows) {
		return itemService.list(page,rows);
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public ItemSaveResult  save(@ModelAttribute ItemFormBean itemFormBean){	
		Date now = new Date();
		
		TbItem item = itemFormBean.item();
		item.setStatus((byte)1);
		item.setCreated(now);
		item.setUpdated(now);
		TbItemParam itemParam = itemFormBean.itemParam();
		itemParam.setCreated(now);
		itemParam.setUpdated(now);
		TbItemDesc itemDesc = itemFormBean.itemDesc();
		itemDesc.setCreated(now);
		itemDesc.setUpdated(now);
		
		itemService.save(item,itemParam,itemDesc); 
		return ItemSaveResult.OK;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object saveExceptionHandle(Exception e) {
	
		return ItemSaveResult.FAILED;
		
	}
	
	/*public ItemDeleteResult delete()throws Exception{
		
	}*/
	
	
	
	
	
}
