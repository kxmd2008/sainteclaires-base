package org.luis.sainteclaires.base.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.luis.basic.domain.BaseEntity;

/**
 * 评论
 * 
 * @author Guoliang.Li
 * 
 */
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 4948375675478372055L;
	private Long picId;
	private Long commentatorId;
	private String comment;// 评论内容
	private Long parentId;
	private Date date;

	@Transient
	private String name;
	@Transient
	private String email;
	@Transient
	private List<Comment> comments;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Comment> getComments() {
		if (comments == null) {
			comments = new ArrayList<Comment>();
		}
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Long getCommentatorId() {
		return commentatorId;
	}

	public void setCommentatorId(Long commentatorId) {
		this.commentatorId = commentatorId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String toString() {
		return "id=" + getId() + ", parentId=" + parentId + ", comment="
				+ comment + ", commentator=" + name + ", date=" + date;
	}

}
