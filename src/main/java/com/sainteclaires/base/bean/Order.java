package com.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import org.luis.basic.domain.BaseEntity;

public class Order extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6624377395219783412L;
	private String no;// 订单号
	private String orderDate;// 下单日期
	private long orderTime;// 下单时间
	private String tradeDate;
	private long tradeTime;// 下单时间
	private String account;// 客户帐号
	private BigDecimal amount;// 订单金额
	private Integer status;// 订单状态

	private List<OrderDetail> details;// 订单明细

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public long getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(long tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

}
