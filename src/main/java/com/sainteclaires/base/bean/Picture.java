package com.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

public class Picture extends BaseEntity {
	private static final long serialVersionUID = 7231158911559060931L;
	private String icon;
	private Product product;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
