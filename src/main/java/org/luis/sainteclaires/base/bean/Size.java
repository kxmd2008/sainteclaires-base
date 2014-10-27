package org.luis.sainteclaires.base.bean;

/**
 * 尺码
 * 
 * @author guoliang.li
 * 
 */
public enum Size {

	MESES0("0m"), MESES03("3m"), MESES06("6m"), MESES09("9m"), MESES12("12m");
	private String name;
	
	private Size(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		System.out.println(MESES0.getName());
	}

}
