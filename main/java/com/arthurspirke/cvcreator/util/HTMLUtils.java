package com.arthurspirke.cvcreator.util;

import java.util.Map;
import java.util.Set;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.enums.Types;
import com.arthurspirke.cvcreator.service.PlacesService;

public class HTMLUtils {


	public static String getOptionListHTML(String[] arrayOfTypes){
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < arrayOfTypes.length; i++){
			sb.append("<option value=\"" + arrayOfTypes[i] + "\">" + arrayOfTypes[i] + "</option>");
		}
		return sb.toString();
	}
	
  public static String getOptionListHTMLWithDisabled(String value){
	  return "<option value=\"" + value + "\" disabled>" + value + "</option>";
  }
	
	public static String getOptionsListHTMLByType(String[] arrayOfTypes, Types selectedType){
		StringBuffer sb = new StringBuffer();
		//TODO: work there!
		for(int i = 0; i < arrayOfTypes.length; i++){
			if(arrayOfTypes[i].equals(selectedType)){
				sb.append("<option value=\"" + arrayOfTypes[i] + "\" selected>" + arrayOfTypes[i] + "</option>");
			} else {
				sb.append("<option value=\"" + arrayOfTypes[i] + "\">" + arrayOfTypes[i] + "</option>");
			}
		}
		return sb.toString();
	}
	

   public static String getOptionsCountriesList(Language lang, int selectedPlaceId){
	   PlacesService service = new PlacesService(lang);
	   Map<Integer, String> countryMap = service.getCountryIdNameMap();
	   return getOptionsListBody(countryMap, selectedPlaceId).toString();	
   }
   
   
   public static String getOptionsRegionsList(Language lang, Integer selectedPlaceId, Integer countryId){
	   if(countryId.equals(0)) return "";
	   PlacesService service = new PlacesService(lang);
	   Map<Integer, String> regionMap = service.getRegionIdNameMap(countryId);
	   return getOptionsListBody(regionMap, selectedPlaceId).toString();	
   }
   
   
   public static String getOptionsCitiesList(Language lang, Integer selectedPlacesId, Integer regionId){
       if(regionId.equals(0)) return "";
       PlacesService service = new PlacesService(lang);
       Map<Integer, String> cityMap = service.getCityIdNameMap(regionId);
	   return getOptionsListBody(cityMap, selectedPlacesId).toString();	   
   }
   
   

   private static StringBuffer getOptionsListBody(Map<Integer, String> placesNames, Integer selectedPlaceId){
	   StringBuffer sb = new StringBuffer();
	   Set<Integer> keySet = placesNames.keySet();
	   for(Integer key : keySet){
		   String name = placesNames.get(key);
		   if(selectedPlaceId.equals(key)){
			   sb.append("<option id=\"" + key + "\" value=\"" + name + "\" selected>" + name + "</option>");
		   } else {
			   sb.append("<option id=\"" + key + "\" value=\"" + name + "\">" + name + "</option>");
		   }
	   }
	   return sb;
   }
}
