package com.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 产品快照
 * 
 * @author guoliang.li
 */
@Entity
@Table(name = "product_shot")
public class ProductShot extends BaseEntity {
	private static final long serialVersionUID = -3953358065639095508L;
	@Column(length=50, name = "product_no")
	private String productNo;
	private Integer number = Integer.valueOf(1);

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
