package com.arthurspirke.cvcreator.entity;

public class Region implements Places {

    private final int id;
    private final int countryId;
    private final String ruName;
    private final String enName;
    
	public Region(int id, int countryId, String ruName, String enName) {
		super();
		this.id = id;
		this.countryId = countryId;
		this.ruName = ruName;
		this.enName = enName;
	}

	public int getId() {
		return id;
	}


	public int getCountryId() {
		return countryId;
	}


	public String getRuName() {
		return ruName;
	}


	public String getEnName() {
		return enName;
	}


}
