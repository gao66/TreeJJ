package com.taotao.manager.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.easyui.EasyUIDataGridResult;
import com.taotao.exception.ItemListException;
import com.taotao.exception.ItemSaveException;
import com.taotao.manager.service.ItemService;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public void save(TbItem item, TbItemParam itemParam, TbItemDesc itemDesc) {
		// TODO Auto-generated method stub
		item.setId((long)UUID.randomUUID().hashCode());
		itemMapper.insertSelective(item);
		itemParamMapper.insertSelective(itemParam);
		itemDesc.setItemId(item.getId());
		itemDescMapper.insertSelective(itemDesc);
		
	}



	@Override
	public EasyUIDataGridResult<TbItem> list(Integer page, Integer rows) {
		//PageHelper.startPage(page, rows);
		//List<TbItem> list = itemMapper.selectByExample(null);
		System.out.println("page="+page+",rows="+rows);
		PageInfo<TbItem> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(()->itemMapper.selectByExample(null));
		System.out.println("pageInfo="+pageInfo);
		EasyUIDataGridResult<TbItem> result = new EasyUIDataGridResult<>();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
