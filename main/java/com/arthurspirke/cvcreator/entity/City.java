package com.arthurspirke.cvcreator.entity;

public class City implements Places {
	private final int id;
	private final int regionId;
	private final int countryId;
	private final String ruName;
	private final String enName;

	public City(int id, int regionId, int countryId,
			String ruName, String enName) {
		super();
		this.id = id;
		this.regionId = regionId;
		this.countryId = countryId;
		this.ruName = ruName;
		this.enName = enName;
	}

	public int getId() {
		return id;
	}

	public int getRegionId() {
		return regionId;
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
