package org.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {
	private static final long serialVersionUID = -2635766712923169442L;
	@Column(length = 50, name = "login_name")
	private String loginName;
	@Column(length = 50, name = "cust_name")
	private String custName;// 收件人
	@Column(length = 200)
	private String address;
	@Column(length = 10)
	private String post;// 邮编
	@Column(length = 14) //电话号码
	private String telphone;
	
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

}
