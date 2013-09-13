package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.CityDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.CountryDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.RegionDAO;
import com.arthurspirke.cvcreator.dblayer.factories.DAOFactoryProducer;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.support.Places;

public final class PlacesService {
    private final CountryDAO countryDAO = DAOFactoryProducer.getFactory(EntityType.COUNTRY).getCountryDAO();
    private final RegionDAO regionDAO = DAOFactoryProducer.getFactory(EntityType.REGION).getRegionDAO();
    private final CityDAO cityDAO = DAOFactoryProducer.getFactory(EntityType.CITY).getCityDAO();
    private final Language language;
    
    public PlacesService(Language language){
    	this.language = language;
    }
    
    public PlacesService(String stringLanguage){
    	this.language = Language.getLanguage(stringLanguage);
    }
    
	public String getCountryNameByPrefLang(int countryId){
		   return getPlaceNameByPrefLang(countryId, "country");
	}
	
	public String getRegionNameByPrefLang(int regionId){
		return getPlaceNameByPrefLang(regionId, "region");
	}
	
	public String getCityNameByPrefLang(int cityId){
		return getPlaceNameByPrefLang(cityId, "city");
	}
	
	
	public Map<String, Integer> getCountryIdNameMap(){
		List<Places> countries = countryDAO.getPlacesList();
		return getIdNameMap(countries);
	}
	
	public Map<String, Integer> getRegionIdNameMap(int countryId){
		List<Places> regions = regionDAO.getListByMainPlaceId(countryId);
        return getIdNameMap(regions);
	}
	
	public Map<String, Integer> getCityIdNameMap(int regionId){
		List<Places> cities = cityDAO.getListByMainPlaceId(regionId);
		return getIdNameMap(cities);
	}
	
	
	protected Map<String, Integer> getIdNameMap(List<Places> places){
		
		Map<String, Integer> countryIdNameMap = new TreeMap<>();
		
		for(Places place : places){
			countryIdNameMap.put(placeNameByLang(place), place.getId());
		}
		
		return countryIdNameMap;
	}
	
	protected String placeNameByLang(Places place){
		String placeName = "";
		
		if(language.equals(Language.RUSSIAN)){
			placeName = place.getRuName();
		} else if(language.equals(Language.ENGLISH)){
			placeName = place.getEnName();
		} else {
			throw new IllegalArgumentException();
		}
		
		return placeName;
	}
	
	protected String getPlaceNameByPrefLang(int placeId, String placeType){
		Places place = getPlace(placeId, placeType);
		return placeNameByLang(place);
	}
	
	protected Places getPlace(int placeId, String placeType){
		Places place = null;
		
		if("country".equals(placeType)){
			
            place = countryDAO.getById(placeId);
			
		} else if("region".equals(placeType)){
			
			place = regionDAO.getById(placeId);
			
		} else if("city".equals(placeType)){
			
			place = cityDAO.getById(placeId);
			
		} else {
			throw new IllegalArgumentException();
		}
		
		return place;
	}
	
	
	
}
