package org.luis.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

/**
 * 图片点赞记录
 * 
 * @author Guoliang.Li
 * 
 */
public class PicLove extends BaseEntity {

	private static final long serialVersionUID = 5820493521594866222L;
	private Long picId;
	private Long commentatorId;

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

}
