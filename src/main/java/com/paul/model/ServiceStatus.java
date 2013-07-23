package com.paul.model;

public enum ServiceStatus {

	WAITRESPONSE("待回复",0),UNDERREPAIR("维修中",1),UNCLAIMED("待领取",2),GETED("已领取",3);
	 // 成员变量
    private String name;
    private int index;
    
	private ServiceStatus(String name,Integer index){
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
//	public static void main(String[] args){
//		System.out.println(ServiceStatus.values()[0].getName());
//	}
}
