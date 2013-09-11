package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.util.Utils;

public class JSONGenerator {
	
	
	
	
	public static JSONObject getJsonObjectLocaleFields(Map<String, String> map){
		JSONObject obj = new JSONObject();
		obj.put("staticFieldsInfo", getJSONReflectionByStringMap(map));
		obj.put("staticFieldsName", getJsonArrayFromSet(map.keySet()));
		return obj;
	}
	
	
	@Deprecated
	public static JSONObject getJsonObjectLocaleArrayFields(Map<String, List<String>> map){
		JSONObject obj = new JSONObject();
		obj.put("staticFieldsArrayInfo", getJSONReflectionByListStringMap(map));
		obj.put("staticFieldsArrayName", getJsonArrayFromSet(map.keySet()));
		return obj;
	}
	
	public static JSONObject getJsonObjectLocaleCountryList(Map<Integer, String> map){
		JSONObject obj = new JSONObject();
		obj.put(".selectCountryClass", getJSONReflectionByMapIntegerString(map));
		return obj;
	}
	
	public static JSONObject getJsonObjectLocaleErros(Map<String, String> map){
		JSONObject obj = new JSONObject();
		obj.put("errorFieldsInfo", getJSONReflectionByStringMap(map));
		obj.put("errorFieldsName", map.keySet());
		obj.put("haveErrorData", true);
		return obj;
	}

	private static JSONObject getJSONReflectionByStringMap(Map<String, String> map){
		JSONObject jsonObj = new JSONObject();
		Set<String> keys = map.keySet();
		
		for(String k : keys){
			jsonObj.put(k, map.get(k));
		}
		
		return jsonObj;
	}
	
	
	public static JSONObject getJSONReflectionByObjectMap(Map<String, Object> map){
		JSONObject jsonObj = new JSONObject();
		Set<String> keys = map.keySet();
		
		for(String k : keys){
			jsonObj.put(k, map.get(k));
		}
		
		return jsonObj;
	}
	
	
	public static JSONArray getJSONReflectionByMapIntegerString(Map<Integer, String> map){
		JSONObject obj = null;
		JSONArray array = new JSONArray();
		Set<Integer> keySet = map.keySet();
		
		for(Integer key : keySet){
			obj = new JSONObject();
			obj.put("placeId", key);
			obj.put("placeName", map.get(key));
			array.add(obj);
		}
		
		return array;
	}
	
	

	
	
	public static JSONObject getJSONReflectionByListStringMap(Map<String, List<String>> map){
		JSONObject jsonObj = new JSONObject();
		Set<String> keys = map.keySet();
		
		for(String k : keys){
			jsonObj.put(k, map.get(k));
		}
		
		return jsonObj;
	}
	
	
	private static JSONArray getJsonArrayFromSet(Set<String> keySet){
		JSONArray array = new JSONArray();
		for(String s : keySet){
			array.add(String.valueOf(s));
		}
		
		return array;
	}
	
	
	public static JSONArray getJSONArrayFromPlacesData(List<Map<String, String>> list, Language lang){

        JSONArray responseArray = new JSONArray();
        
        for(int i = 0; i < list.size(); i++){
       	 Map<String, String> map = list.get(i);
       	 Integer countryId = Utils.getInteger(map.get("countryId"));
       	 Integer regionId = Utils.getInteger(map.get("regionId"));
       	 Integer cityId = Utils.getInteger(map.get("cityId"));
       	 String blockId = map.get("selectBlockId");

       	 PlacesService placesService = new PlacesService(lang);
       	 
       	 Map<Integer, String> countryMap = placesService.getCountryIdNameMap();
       	 Map<Integer, String> regionMap = placesService.getRegionIdNameMap(countryId);
       	 Map<Integer, String> cityMap = placesService.getCityIdNameMap(regionId);

       	 JSONObject tempJsonObject = new JSONObject();
       	 tempJsonObject.put("countryId", String.valueOf(countryId));
       	 tempJsonObject.put("countryList", JSONGenerator.getJSONReflectionByMapIntegerString(countryMap));
       	 tempJsonObject.put("regionId", String.valueOf(regionId));
         tempJsonObject.put("regionList", JSONGenerator.getJSONReflectionByMapIntegerString(regionMap));
       	 tempJsonObject.put("cityId", String.valueOf(cityId));
       	 tempJsonObject.put("cityList", JSONGenerator.getJSONReflectionByMapIntegerString(cityMap));
       	 tempJsonObject.put("selectBlockId", blockId);
       	 responseArray.add(tempJsonObject);

        }
        
        return responseArray;
	}
	
}
