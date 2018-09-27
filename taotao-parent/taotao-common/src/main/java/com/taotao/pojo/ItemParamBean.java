package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItemParamBean implements Serializable{
	//<th data-options="field:'ck',checkbox:true"></th>
	private Long id; //<th data-options="field:'id',width:60">ID</th>
	private Long itemCatId; //<th data-options="field:'itemCatId',width:80">商品类目ID</th>
	private String itemCatName; //<th data-options="field:'itemCatName',width:100">商品类目</th>
    private String paramData; // <th data-options="field:'paramData',width:300,formatter:formatItemParamData">规格(只显示分组名称)</th>
   
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date created; //<th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updated; // <th data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}
	public String getItemCatName() {
		return itemCatName;
	}
	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
    
}
