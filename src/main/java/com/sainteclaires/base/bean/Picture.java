package com.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "picture")
public class Picture extends BaseEntity {
	private static final long serialVersionUID = 7231158911559060931L;
	@Column(length=50)
	private String icon;
	@Column(length=50)
	private String productNo;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

}
