package com.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.luis.basic.domain.BaseEntity;

/**
 * 购物车
 */
@Entity
@Table(name = "shopping_bag")
public class ShoppingBag extends BaseEntity {
	private static final long serialVersionUID = 7364592180210908494L;
	@Transient
	private List<ProductShot> productShots;
	@Column(length = 50)
	private String customerNo;
	@Column(length = 50)
	private String t;// t 表示放到用户浏览器端的cookie值，要求unique。
	@Column(length = 50)
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

	public List<ProductShot> getProductShots() {
		return productShots;
	}

	public void setProductShots(List<ProductShot> productShots) {
		this.productShots = productShots;
	}

}
