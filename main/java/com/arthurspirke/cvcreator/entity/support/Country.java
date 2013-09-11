package com.arthurspirke.cvcreator.entity.support;

public class Country extends Places{

	
	public Country(int id, String ruName, String enName) {
		super(id, ruName, enName);
	}


	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Country country = (Country) o;
		
		boolean idCheck = id == country.id;
		boolean ruNameCheck = ruName == country.ruName || (ruName != null && ruName.equals(country.ruName));
		boolean enNameCheck = enName == country.enName || (enName != null && enName.equals(country.getEnName()));
		
		return idCheck && ruNameCheck && enNameCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + id;
		hash = hash * 31 + (ruName == null ? 0 : ruName.hashCode());
		hash = hash * 31 + (enName == null ? 0 : enName.hashCode());
		return hash;
	}


}
