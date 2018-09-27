package com.taotao.ui.form.bean;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;

public class ItemFormBean {
	private Long cid;//=[3]
	private String title;//		title=[aaaaaa]
	private String sellPoint;//		sellPoint=[aaaaaa]
	private Double priceView;//		priceView=[12.00]
	private Long price;//		price=[1200]
	private Integer num; //	num=[23]
	private String barcode; //		barcode=[1122111]
	private String image; //		image=[http://localhost/download/upload/image/2018-09-15-06-44-51-71020c7b-e629-4635-90f3-0bd7eeb383e7.jpg]
	private String desc; //		desc=
	private String itemParams; //		itemParams=[]
	
	
	public TbItem item() {
		TbItem item = new TbItem();
		item.setCid(cid);
		item.setImage(image);
		item.setNum(num);
		item.setPrice(price);
		item.setSellPoint(sellPoint);
		item.setBarcode(barcode);
		item.setTitle(title);
		return item; 
	}
	
	public TbItemParam itemParam() {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(itemParams);
		return itemParam;
		
	}
	
	public TbItemDesc itemDesc() {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		return itemDesc;
		
	} 
	
	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Double getPriceView() {
		return priceView;
	}
	public void setPriceView(Double priceView) {
		this.priceView = priceView;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getItemParams() {
		return itemParams;
	}
	public void setItemParams(String itemParams) {
		this.itemParams = itemParams;
	}
	
}
