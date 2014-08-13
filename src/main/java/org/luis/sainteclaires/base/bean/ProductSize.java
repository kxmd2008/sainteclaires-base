package org.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 产品尺码，库存数量
 * @author Guoliang.Li
 */
@Entity
@Table(name = "product_size")
public class ProductSize extends BaseEntity {
	
	private static final long serialVersionUID = -8785503740676125616L;
	@Column(length=30)
	private String size;
	@Column(name = "product_id")
	private Long productId;
	// 库存数量
	private Integer num;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
