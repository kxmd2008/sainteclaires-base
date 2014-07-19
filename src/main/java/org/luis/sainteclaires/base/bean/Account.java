package org.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {
	public static final String TYPE_ADMIN = "ADMIN";
	public static final String TYPE_CUSTOMER = "CUSTOMER";
	private static final long serialVersionUID = 178723493562907433L;
	@Column(length = 50, name = "login_name")
	private String loginName;
	@Column(length = 50)
	private String password;
	@Column(length = 50, name = "cust_name")
	private String custName;// 客户名，收件人
	@Column(length = 200)
	private String address;
	@Column(length = 20)
	private String phone;// 手机
	@Column(length = 10)
	private String post;// 邮编
	@Column(length = 10)
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
