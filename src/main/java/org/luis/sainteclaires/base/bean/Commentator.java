package org.luis.sainteclaires.base.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 评论人
 * 
 * @author Guoliang.Li
 * 
 */
@Entity
@Table(name = "commentator")
public class Commentator extends BaseEntity {

	private static final long serialVersionUID = -4387474362304728524L;
	private String name;// 评论人名字
	private String email;// 评论人邮件

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
