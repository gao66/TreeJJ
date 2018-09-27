package com.taotao.manager.service;

import com.taotao.easyui.EasyUIDataGridResult;
import com.taotao.pojo.ItemParamBean;

public interface ItemParamService {
	
	EasyUIDataGridResult<ItemParamBean> list(Integer page, Integer rows);
}
