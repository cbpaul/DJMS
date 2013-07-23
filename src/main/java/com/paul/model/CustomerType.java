package com.paul.model;

public enum CustomerType {
	COMMON("普通客户",0),MERCHANT("商家",1);
	
	private String name;
	private Integer index;
	private CustomerType(String name,Integer index){
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
	 public  static CustomerType fromInt(int value) {   
         switch(value) {
             case 1: return COMMON;
             case 2: return MERCHANT;
             default : return null;
         }
    }
	
}
