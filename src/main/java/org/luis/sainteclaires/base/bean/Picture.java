package org.luis.sainteclaires.base.bean;

import java.util.ArrayList;
import java.util.List;

import org.luis.basic.domain.BaseEntity;

public class Picture extends BaseEntity {
	private static final long serialVersionUID = 7231158911559060931L;
	private List<String> pics = new ArrayList<String>();

	private String name;

	private String picStr;

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
