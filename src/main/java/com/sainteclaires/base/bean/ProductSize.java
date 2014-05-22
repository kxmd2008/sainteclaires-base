package com.sainteclaires.base.bean;

import java.util.List;

import org.luis.basic.domain.BaseEntity;

public class ProductSize extends BaseEntity {
	private static final long serialVersionUID = -6084029923513319887L;
	private String size;
	private List<Product> products;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
