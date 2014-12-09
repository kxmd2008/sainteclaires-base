package org.luis.sainteclaires.base.bean;

import java.util.ArrayList;
import java.util.List;

public class Picture {
	private Long id;
	private List<String> pics = new ArrayList<String>();

	private String name;

	private String picStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicStr() {
		return picStr;
	}

	public void setPicStr(String picStr) {
		this.picStr = picStr;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
