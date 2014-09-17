package org.luis.sainteclaires.base.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

	private static final long serialVersionUID = -4680639268444282632L;
	@Column(length = 50)
	private String name;// 产品名称
	@Column(length = 200)
	private String description;// 产品描述
	@Column(name = "category_id")
	private Long categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@Column(length = 50, name = "product_no")
	private String productNo;// 产品编号
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<ProductSize> psizes;// 尺码
	private BigDecimal price;// 产品价格
	private Integer num;// 产品库存
	// 浏览量
	private Integer viewNum;
	private String pics;
	private Integer status = 0;
	private Boolean isNew = true;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// private List<Picture> pics;

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getDescription() {
		return description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

//	public List<ProductSize> getPsizes() {
//		return psizes;
//	}
//
//	public void setPsizes(List<ProductSize> psizes) {
//		this.psizes = psizes;
//	}

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

}
