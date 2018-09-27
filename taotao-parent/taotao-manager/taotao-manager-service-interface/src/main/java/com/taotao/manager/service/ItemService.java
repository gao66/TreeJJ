package com.taotao.manager.service;

import com.taotao.easyui.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;

public interface ItemService {
	void save(TbItem item, TbItemParam itemParam,TbItemDesc itemDesc);

	EasyUIDataGridResult<TbItem> list(Integer page, Integer rows) ;
}
