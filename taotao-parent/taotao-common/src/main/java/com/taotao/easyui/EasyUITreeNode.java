package com.taotao.easyui;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 每个节点可以包括下列属性：

    id：节点的 id，它对于加载远程数据很重要。
    text：要显示的节点文本。
    state：节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
    checked：指示节点是否被选中。
    attributes：给一个节点添加的自定义属性。
    children：定义了一些子节点的节点数组。

 
 * */
public class EasyUITreeNode implements Serializable{
	private Long id;
	private String text;
	private String state; 
	private boolean checked;
	private Map<String,Object> attributes;
	private List<EasyUITreeNode> children;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<EasyUITreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITreeNode> children) {
		this.children = children;
	}
	
}
