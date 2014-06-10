package com.sainteclaires.base.bean;

/**
 * 产品快照
 * @author guoliang.li
 *
 */
public class ProductShot {
	private Product product;
	private Integer number = Integer.valueOf(1);

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
