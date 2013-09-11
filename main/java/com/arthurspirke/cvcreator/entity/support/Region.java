package com.arthurspirke.cvcreator.entity.support;

public class Region extends Places {

    private final int countryId;

    
	public Region(int id, int countryId, String ruName, String enName) {
		super(id, ruName, enName);
		this.countryId = countryId;
	}
	
	public int getCountryId() {
		return countryId;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Region region = (Region) o;
		
		boolean idCheck = id == region.getId();
		boolean countryIdCheck = countryId == region.getCountryId();
		boolean ruNameCheck = ruName == region.ruName || (ruName != null && ruName.equals(region.getRuName()));
		boolean enNameCheck = enName == region.enName || (enName != null && enName.equals(region.getEnName()));
		
		return idCheck && countryIdCheck && ruNameCheck && enNameCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + id;
		hash = hash * 31 + countryId;
		hash = hash * 31 + (ruName == null ? 0 : ruName.hashCode());
		hash = hash * 31 + (enName == null ? 0 : enName.hashCode());
		return hash;
	}


}
