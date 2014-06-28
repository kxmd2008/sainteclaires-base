package com.luis.sainteclaires.base.bean;

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
	@Column(length = 50, name = "cust_no")
	private String custNo;
	@Column(length = 50)
	private String t;// t 表示放到用户浏览器端的cookie值，要求unique。
	@Column(length = 50, name = "session_id")
	private String sessionId;
	@Column(name = "total_amount")
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

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
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
