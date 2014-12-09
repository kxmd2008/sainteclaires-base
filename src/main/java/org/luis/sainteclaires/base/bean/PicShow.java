package org.luis.sainteclaires.base.bean;

import org.luis.basic.domain.BaseEntity;

/**
 * 展示图片
 * 
 * @author Guoliang.Li
 * 
 */
public class PicShow extends BaseEntity {
	private static final long serialVersionUID = 2201291750348204309L;

	private String name;

	private String path;// 文件路径

	private Long cateId;// 类别

	private Integer views;// 浏览量

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

}
