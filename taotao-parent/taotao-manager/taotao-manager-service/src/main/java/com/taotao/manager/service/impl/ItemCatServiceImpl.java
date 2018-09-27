package com.taotao.manager.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.easyui.EasyUITreeNode;
import com.taotao.manager.service.ItemCatService;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper itemCatMapper; 
	
	@Override
	public List<EasyUITreeNode> list(Long id) throws Exception {
		
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EasyUITreeNode> nodeList = new LinkedList<>();
		for(TbItemCat cat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		
		return nodeList;
	}

}
