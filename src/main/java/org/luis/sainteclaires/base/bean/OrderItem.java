package org.luis.sainteclaires.base.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {
	private static final long serialVersionUID = 7892163388246757549L;
	@Column(length=50, name = "product_no")
	private String productNo;// 购买产品
	private int num;// 购买数量
	private BigDecimal price;// 当时产品价格，如果没下单，下次进来应该是按最新的价格显示

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
