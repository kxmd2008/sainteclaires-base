package com.sainteclaires.base.bean;

import java.math.BigDecimal;

import org.luis.basic.domain.BaseEntity;

public class OrderDetail extends BaseEntity {
	private static final long serialVersionUID = 7892163388246757549L;
	private Product product;// 购买产品
	private int num;// 购买数量
	private BigDecimal price;// 当时产品价格，如果没下单，下次进来应该是按最新的价格显示

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
