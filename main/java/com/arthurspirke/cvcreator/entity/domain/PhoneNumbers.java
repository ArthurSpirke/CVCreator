package com.arthurspirke.cvcreator.entity.domain;

import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;

public class PhoneNumbers {
	private final int id;
	private final int personId;
	private final PhoneIcon phoneType;
    private final String phoneNumber;
    private final String state;
    
    public PhoneNumbers(int id, int personId, PhoneIcon phoneType, String phoneNumber){
    	this(id, personId, phoneType, phoneNumber, "");
    }

	public PhoneNumbers(int id, int personId, PhoneIcon phoneType, String phoneNumber, String state) {
		super();
		this.id = id;
		this.personId = personId;
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

	public PhoneIcon getPhoneType() {
		return phoneType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getState() {
		return state;
	}
	
}
