package org.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 类别、产品关联表，一个产品可能属于多个类别
 * 
 * @author Guoliang.Li
 * 
 */
@Entity
@Table(name = "category_product")
public class CategoryProduct extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4672030599319322177L;
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "category_id")
	private Long categoryId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
