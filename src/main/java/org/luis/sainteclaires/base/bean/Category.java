package org.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category extends BaseEntity {

	private static final long serialVersionUID = -6305965741752477104L;
	@Column(length = 50)
	private String name;//
	@Column(name = "parent_id")
	private Long parentId;// parentId不为null，为二级类别
	@Column(name = "parent_name")
	private String parentName = "";
	@Column(name = "order_no")
	private Integer orderNo;// 序号
	@Transient
	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
				.append(this.getName()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Category cat = (Category) obj;
		return new EqualsBuilder().append(this.getId(), cat.getId()).isEquals();
	}

}
