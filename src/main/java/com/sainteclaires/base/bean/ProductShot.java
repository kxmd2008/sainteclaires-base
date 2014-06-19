package com.sainteclaires.base.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 产品快照
 * @author guoliang.li
 */
@Entity
@Table(name = "product_shot")
public class ProductShot extends BaseEntity {
	private static final long serialVersionUID = -3953358065639095508L;
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
