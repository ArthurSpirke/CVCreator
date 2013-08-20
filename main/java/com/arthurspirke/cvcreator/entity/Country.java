package com.arthurspirke.cvcreator.entity;

public class Country implements Places{
	private final int id;
	private final String ruName;
	private final String enName;

	
	
	public Country(int id, String ruName, String enName) {
		super();
		this.id = id;
		this.ruName = ruName;
		this.enName = enName;
	}

	public int getId() {
		return id;
	}

	public String getRuName() {
		return ruName;
	}

	public String getEnName() {
		return enName;
	}


}
