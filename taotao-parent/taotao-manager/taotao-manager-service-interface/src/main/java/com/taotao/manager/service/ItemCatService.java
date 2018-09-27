package com.taotao.manager.service;

import java.util.List;

import com.taotao.easyui.EasyUITreeNode;

public interface ItemCatService {
	
	List<EasyUITreeNode> list(Long id) throws Exception;
}
