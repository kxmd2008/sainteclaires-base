package com.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 购物车
 */
@Entity
@Table(name = "shopping_bag")
public class ShoppingBag extends BaseEntity {
	private static final long serialVersionUID = 7364592180210908494L;
	private List<ProductShot> productShots;
	private String customerNo;
	private String t;// t 表示放到用户浏览器端的cookie值，要求unique。
	private String sessionId;
	private BigDecimal totalAmount;
	private Long timestamp;

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

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

	public List<ProductShot> getProductShots() {
		return productShots;
	}

	public void setProductShots(List<ProductShot> productShots) {
		this.productShots = productShots;
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
