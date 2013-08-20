package com.arthurspirke.cvcreator.dblayer;

import java.util.List;

import com.arthurspirke.cvcreator.entity.Places;

public class PlaceDBManager {

	
	public static List<Places> getCountryList(){
		CountryDAO dao = new CountryDAO();
		return dao.getListOfCountries();
	}
}
