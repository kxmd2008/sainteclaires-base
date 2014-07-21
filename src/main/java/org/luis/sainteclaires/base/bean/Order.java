package org.luis.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "cust_order")
public class Order extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6624377395219783412L;
	@Column(length = 50, name = "order_no")
	private String orderNo;// 订单号
	@Column(length = 8, name = "order_date")
	private String orderDate;// 下单日期
	@Column(name = "order_time")
	private long orderTime;// 下单时间
	@Column(length = 8, name = "trade_date")
	private String tradeDate;// 付款日期
	@Column(name = "trade_time")
	private long tradeTime;// 付款时间
	@Column(length = 50)
	private String account;// 客户帐号
	private BigDecimal amount;// 订单金额
	private Integer status;// 订单状态 0:待付款；1：已付款待处理；2：已发货；3：已收货
	@Transient
	private List<OrderItem> items;// 订单明细

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
