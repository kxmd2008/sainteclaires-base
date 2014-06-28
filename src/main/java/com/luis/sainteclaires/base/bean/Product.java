package com.luis.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {
	
	private static final long serialVersionUID = -4680639268444282632L;
	@Column(length=50)
	private String name;// 产品名称
	@Column(length=50, name = "product_no")
	private String productNo;// 产品编号
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ProductSize> prodSizes;// 尺码
	private BigDecimal price;// 产品价格
	private Integer num;// 产品库存

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public List<ProductSize> getProdSizes() {
		return prodSizes;
	}

	public void setProdSizes(List<ProductSize> prodSizes) {
		this.prodSizes = prodSizes;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
