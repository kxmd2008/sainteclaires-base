package org.luis.sainteclaires.base.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductVo implements Serializable {
	private static final long serialVersionUID = -8880563754187771336L;
	private String name;// 产品名称
	private Long categoryId;
	private String categoryName;
	private String productNo;// 产品编号
	private BigDecimal price;// 产品价格
	private Integer num;// 产品库存
	private Integer xnum;// 产品库存
	private Integer x1num;// 产品库存
	private Integer x2num;// 产品库存
	private List<String> pics;
	private Boolean isnew;
	private String desc;

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

	public Integer getXnum() {
		return xnum;
	}

	public void setXnum(Integer xnum) {
		this.xnum = xnum;
	}

	public Integer getX1num() {
		return x1num;
	}

	public void setX1num(Integer x1num) {
		this.x1num = x1num;
	}

	public Integer getX2num() {
		return x2num;
	}

	public void setX2num(Integer x2num) {
		this.x2num = x2num;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public Boolean getIsnew() {
		return isnew;
	}

	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
