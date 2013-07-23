package com.paul.model;

public enum AccountrecordType {
	REPLACE("换配件",0),INSTALLOS("装系统",1),CLEAN("清洁",2),SALL("卖出",3),OTHER("其它",4);
	private String name;
	private Integer index;
	private AccountrecordType(String name,Integer index){
		this.name = name;
		this.index = index;
	}
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
