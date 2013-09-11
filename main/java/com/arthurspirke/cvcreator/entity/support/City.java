package com.arthurspirke.cvcreator.entity.support;

public class City extends Places{
	private final int regionId;
	private final int countryId;

	public City(int id, int regionId, int countryId,
			String ruName, String enName) {
		super(id, ruName, enName);
		this.regionId = regionId;
		this.countryId = countryId;
	}


	public int getRegionId() {
		return regionId;
	}

	public int getCountryId() {
		return countryId;
	}

	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		City city = (City) o;
		
		boolean idCheck = id == city.getId();
		boolean regionIdCheck = regionId == city.getRegionId();
		boolean countryIdCheck = countryId == city.getCountryId();
		boolean ruNameCheck = ruName == city.ruName || (ruName != null && ruName.equals(city.getRuName()));
		boolean enNameCheck = enName == city.enName || (enName != null && enName.equals(city.getEnName()));
		
		return idCheck && regionIdCheck && countryIdCheck && ruNameCheck && enNameCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + id;
		hash = hash * 31 + regionId;
		hash = hash * 31 + countryId;
		hash = hash * 31 + (ruName == null ? 0 : ruName.hashCode());
		hash = hash * 31 + (enName == null ? 0 : enName.hashCode());
		return hash;
	}

}
