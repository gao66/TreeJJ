package com.taotao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.easyui.EasyUIDataGridResult;
import com.taotao.manager.service.ItemParamService;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.ItemParamBean;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public EasyUIDataGridResult<ItemParamBean> list(Integer page, Integer rows) {
		/*PageHelper.startPage(page, rows);
		
		List<ItemParamBean> list = itemParamMapper.list();
		*/
		PageInfo<ItemParamBean> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(()->itemParamMapper.list());
		EasyUIDataGridResult<ItemParamBean> result = new EasyUIDataGridResult<>();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		
		// TODO Auto-generated method stub
		return result;
	}

}
