package org.luis.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

/**
 * 评论
 * 
 * @author Guoliang.Li
 * 
 */
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 4948375675478372055L;
	private Long picId;
	private Long commentatorId;
	private String comment;// 评论内容
	private Long parentId;

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

}
