package com.sainteclaires.base.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

	private static final long serialVersionUID = -6305965741752477104L;
	private String name;
	private Long parentId;//parentId不为null，为二级类别

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

}
