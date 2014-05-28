package com.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

public class Picture extends BaseEntity {
	private static final long serialVersionUID = 7231158911559060931L;
	private String icon;
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
