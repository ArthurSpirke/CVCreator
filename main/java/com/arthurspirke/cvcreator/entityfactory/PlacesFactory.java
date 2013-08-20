package com.arthurspirke.cvcreator.entityfactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.arthurspirke.cvcreator.dblayer.CityDAO;
import com.arthurspirke.cvcreator.dblayer.CountryDAO;
import com.arthurspirke.cvcreator.dblayer.RegionDAO;
import com.arthurspirke.cvcreator.entity.City;
import com.arthurspirke.cvcreator.entity.Country;
import com.arthurspirke.cvcreator.entity.Places;
import com.arthurspirke.cvcreator.entity.Region;
import com.arthurspirke.cvcreator.entity.enums.Language;

public class PlacesFactory {

	
	public static Country getCountryById(int countryId){
		return new CountryDAO().getCountryById(countryId);
	}
	
	public static Region getRegionById(int regionId){
		return new RegionDAO().getRegionById(regionId);
	}
	
	public static City getCityById(int cityId){
		return new CityDAO().getCityById(cityId);
	}
	
	
	public static List<Places> getFullCountryList(){
		return new CountryDAO().getListOfCountries();
	}
	
	public static List<Places> getRegionListByCountryId(int countryId){
		return new RegionDAO().getListOfRegionsByCountryId(countryId);
	}
	
	public static List<Places> getCityListByCountryId(int regionId){
		return new CityDAO().getCityListByRegionId(regionId);
	}
	
	//TODO: performance problem.
	public static Map<Integer, Places> getCountryMap(){
		List<Places> countryList = getFullCountryList();
		Map<Integer, Places> countryMap = new HashMap<>(countryList.size());
		
		for(Places country : countryList){
			countryMap.put(country.getId(), country);
		}
		
		return countryMap;
	}
	
	
	public static Map<Integer, Places> getRegionMap(int countryId){
		if(countryId == 0) return Collections.emptyMap();
		List<Places> regionList = getRegionListByCountryId(countryId);
		Map<Integer, Places> regionMap = new HashMap<>(regionList.size());
		
		for(Places region : regionList){
			regionMap.put(region.getId(), region);
		}
		
		return regionMap;
	}
	
	public static Map<Integer, Places> getCityMap(int regionId){
		if(regionId == 0) return Collections.emptyMap();
		List<Places> cityList = getCityListByCountryId(regionId);
		Map<Integer, Places> cityMap = new HashMap<>(cityList.size());
		
		for(Places city : cityList){
			cityMap.put(city.getId(), city);
		}
		
		return cityMap;
	}
	
	public static Map<Integer, String> getMapPlacesByLang(Map<Integer, Places> map, Language lang){
		Map<Integer, String> returnMap = new HashMap<>(map.size());
		Set<Integer> keySet = map.keySet();
		
		if(lang.equals(Language.RUSSIAN)){
		    for(Integer key : keySet){
		    	returnMap.put(key, map.get(key).getRuName());
		    }
		} else {
		    for(Integer key : keySet){
		    	returnMap.put(key, map.get(key).getEnName());
		    }
		}
		
		return returnMap;
	}
	
	
	public static String getCountryByPrefLang(Integer countryId, Language prefLang){
		Country country = getCountryById(countryId);
        return getPlaceNameByPrefLang(country, prefLang);
	}
	
	
	public static String getRegionByPrefLang(Integer regionId, Language prefLang){
		Region region = getRegionById(regionId);
		return getPlaceNameByPrefLang(region, prefLang);
	}
	
	public static String getCityByPrefLang(Integer cityId, Language prefLang){
		City city = getCityById(cityId);
		return getPlaceNameByPrefLang(city, prefLang);
	}
	
	//use Langauge for prefLang
	private static String getPlaceNameByPrefLang(Places place, Language prefLang){
		if(prefLang.equals(Language.RUSSIAN)){
			return place.getRuName();
		} else if (prefLang.equals(Language.ENGLISH)){
			return place.getEnName();
		} else {
			return "No place";
		}
	}
}
