package com.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

/**
 * 购物车
 */
public class ShoppingBag extends BaseEntity {
	private static final long serialVersionUID = 7364592180210908494L;
	private String productNo;
	private Integer number;
	private String customerNo;
	private String t;// t 表示放到用户浏览器端的cookie值，要求unique。
	private String sessionId;
	private Long timestamp;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

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

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}
