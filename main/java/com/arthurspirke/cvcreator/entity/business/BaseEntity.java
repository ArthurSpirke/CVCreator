package com.arthurspirke.cvcreator.entity.business;

public class BaseEntity {
	
	protected String id;
	protected String state;
	
	public BaseEntity(String id, String state){
		this.id = id;
		this.state = state;
	}
	
	public String getId() {	
		return id;	
	}
	
	public String getState() {
        return state;		
	}
	
}
