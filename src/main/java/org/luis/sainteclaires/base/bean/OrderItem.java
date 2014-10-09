package org.luis.sainteclaires.base.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {
	public static final Integer STATUS_COMMON = 1;
	/**
	 * 退换货
	 */
	public static final Integer STATUS_RETURN = 2;
	private static final long serialVersionUID = 7892163388246757549L;
	@Column(length = 50, name = "product_no")
	private String productNo;// 购买产品
	@Column(name = "order_id")
	private Long orderId;// 购买产品
	private int num;// 购买数量
	private BigDecimal price;// 当时产品价格，如果没下单，下次进来应该是按最新的价格显示
	private Integer status = STATUS_COMMON;
	@Column(name = "product_id")
	private Long productId;
	@Transient
	private String productName;
	@Transient
	private String pic;
	@Transient
	private BigDecimal sum = BigDecimal.ZERO;
	private String size;

	@Column(length = 250)
	private String note;// 退货|换货理由

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getPrice())
				.append(this.getNum()).append(this.getProductId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		OrderItem shot = (OrderItem) obj;
		return new EqualsBuilder().append(this.productId, shot.getProductId())
				.isEquals();
	}

}
