package org.luis.sainteclaires.base.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.luis.basic.domain.BaseEntity;

/**
 * 产品快照
 * 
 * @author guoliang.li
 */
@Entity
@Table(name = "product_shot")
public class ProductShot extends BaseEntity {
	private static final long serialVersionUID = -3953358065639095508L;
	@Column(length = 50, name = "product_no")
	private String productNo;
	@Column(name = "product_id")
	private Long productId;
	private Integer number = Integer.valueOf(1);
	private BigDecimal price;
	@Column(name = "bag_id")
	private Long bagId;
	@Transient
	private String productName;
	@Transient
	private String pic;

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

	public Long getBagId() {
		return bagId;
	}

	public void setBagId(Long bagId) {
		this.bagId = bagId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getPrice())
				.append(this.getNumber()).append(this.getProductId())
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		ProductShot shot = (ProductShot) obj;
		return new EqualsBuilder().append(this.productId, shot.getProductId())
				.isEquals();
	}

}
