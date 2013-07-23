package com.paul.model;

public enum Gender {
	FEMALE("女",0),MALE("男",1);
	private Gender(String name,Integer index){
		this.name = name;
		this.index = index;
	}
	private String name;
	private Integer index;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
