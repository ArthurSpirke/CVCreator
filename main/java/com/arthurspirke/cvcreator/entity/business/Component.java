package com.arthurspirke.cvcreator.entity.business;

public class Component extends BaseEntity{

	protected String personId;
	
	public Component(String id, String personId, String state){
		super(id, state);
		this.personId = personId;
	}
	
	public String getPersonId() {
		return personId;	
	}

	
	
}
