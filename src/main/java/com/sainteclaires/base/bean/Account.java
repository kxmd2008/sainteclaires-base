package com.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

public class Account extends BaseEntity {
	public static final String TYPE_ADMIN = "ADMIN";
	public static final String TYPE_CUSTOMER = "CUSTOMER";
	private static final long serialVersionUID = 178723493562907433L;
	private String loginName;
	private String password;
	private String custName;// 客户名，收件人
	private String address;
	private String phone;// 手机
	private String postCode;// 邮编
	private String type = TYPE_CUSTOMER;// 帐号类型

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
