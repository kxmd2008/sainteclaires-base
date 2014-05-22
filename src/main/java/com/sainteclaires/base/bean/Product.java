package com.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import org.luis.basic.domain.BaseEntity;

public class Product extends BaseEntity {
	
	private static final long serialVersionUID = -4680639268444282632L;
	private String name;// 产品名称
	private String productNo;// 产品编号
	private List<ProductSize> prodSizes;// 尺码
	private List<Picture> pics;// 图片
	private BigDecimal price;// 产品价格
	private Integer num;// 产品库存

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public List<ProductSize> getProdSizes() {
		return prodSizes;
	}

	public void setProdSizes(List<ProductSize> prodSizes) {
		this.prodSizes = prodSizes;
	}

	public List<Picture> getPics() {
		return pics;
	}

	public void setPics(List<Picture> pics) {
		this.pics = pics;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
