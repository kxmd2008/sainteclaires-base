package com.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {
	private static final long serialVersionUID = -2635766712923169442L;
	@Column(length=50)
	private String loginName;
	@Column(length=200)
	private String address;
	@Column(length=10)
	private String postCode;// 邮编

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
