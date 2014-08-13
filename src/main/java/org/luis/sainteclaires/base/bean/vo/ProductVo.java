package org.luis.sainteclaires.base.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVo implements Serializable {
	private static final long serialVersionUID = -8880563754187771336L;
	private Long id;
	private String name;// 产品名称
	private Long categoryId;
	private String categoryName;
	private String productNo;// 产品编号
	private BigDecimal price;// 产品价格
	private Integer num;// 产品库存
	private Integer meses06;// 产品库存
	private Integer meses09;// 产品库存
	private Integer meses12;// 产品库存
	private Integer meses18;// 产品库存
	private Integer meses24;// 产品库存
	private String pics;
	private Boolean isNew;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
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

	public Integer getMeses06() {
		return meses06;
	}

	public void setMeses06(Integer meses06) {
		this.meses06 = meses06;
	}

	public Integer getMeses09() {
		return meses09;
	}

	public void setMeses09(Integer meses09) {
		this.meses09 = meses09;
	}

	public Integer getMeses12() {
		return meses12;
	}

	public void setMeses12(Integer meses12) {
		this.meses12 = meses12;
	}

	public Integer getMeses18() {
		return meses18;
	}

	public void setMeses18(Integer meses18) {
		this.meses18 = meses18;
	}

	public Integer getMeses24() {
		return meses24;
	}

	public void setMeses24(Integer meses24) {
		this.meses24 = meses24;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
