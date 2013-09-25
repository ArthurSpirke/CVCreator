package com.arthurspirke.cvcreator.service;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.util.Utils;

public class JSONGenerator {
	private static Logger log = Logger.getLogger(JSONGenerator.class);
	
	
	public static JSONObject getJsonObject(BufferedReader bf) {
		String line = "";
		JSONParser parser = new JSONParser();
		JSONObject o = null;
		StringBuffer sb = new StringBuffer();
		try {
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
			log.debug("JSON - " + sb.toString());
			o = (JSONObject) parser.parse(sb.toString());
			
			return o;
		} catch (Exception ex) {
			log.error("Error - " + ex);
			return null;
		}
	}
	
	public static JSONObject getJsonObject(String notParseJsonString){
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(notParseJsonString);
			return jsonObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static JSONArray getJsonArray(String notParseJsonString){
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONArray jsonObject = (JSONArray) parser.parse(notParseJsonString);
			return jsonObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
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
	
	public static JSONObject getJsonObjectLocaleCountryList(Map<String, Integer> map){
		JSONObject obj = new JSONObject();
		obj.put(".selectCountryClass", getJSONReflectionByMapIntegerString(map));
		return obj;
	}
	
	public static JSONObject getJsonObjectLocaleErros(Map<String, String> map){
		Set<String> keySet = map.keySet();
		JSONArray j = new JSONArray();
		
		for(String key : keySet){
			j.add(String.valueOf(key));
		}
		
		JSONObject obj = new JSONObject();
		obj.put("errorFieldsInfo", getJSONReflectionByStringMap(map));
		obj.put("errorFieldsName", j);
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
	
	
	public static JSONArray getJSONReflectionByMapIntegerString(Map<String, Integer> map){
		JSONObject obj = null;
		JSONArray array = new JSONArray();
		Set<String> keySet = map.keySet();
		
		for(String key : keySet){
			obj = new JSONObject();
			obj.put("placeId", map.get(key));
			obj.put("placeName", key);
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
       	 
       	 Map<String, Integer> countryMap = placesService.getCountryIdNameMap();
       	 Map<String, Integer> regionMap = placesService.getRegionIdNameMap(countryId);
       	 Map<String, Integer> cityMap = placesService.getCityIdNameMap(regionId);

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
