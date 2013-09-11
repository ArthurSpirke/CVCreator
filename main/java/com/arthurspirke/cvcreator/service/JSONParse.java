package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParse {

/*
	public static <K, V> List<Map<K, V>> getMapFromJsonArray(JSONArray array, K[] keyList){
        List<Map<K, V>> returnList = new ArrayList<>();
		
		for(int i = 0; i < array.size(); i++){
			
			JSONObject obj = (JSONObject) array.get(i);
			Map<K, V> map = new HashMap<>();
			//TODO: bad implementation
			for(K key : keyList){
				map.put(key, (V) obj.get(key));
			}
			
			returnList.add(map);
		}
		
		return returnList;
	}*/
	
	public static List<Map<String, String>> getMapFromJsonArray(JSONArray array, String[] keyList){
        List<Map<String, String>> returnList = new ArrayList<>();
		
		for(int i = 0; i < array.size(); i++){
			
			JSONObject obj = (JSONObject) array.get(i);
			Map<String, String> map = new HashMap<>();
			//TODO: bad implementation
			for(String key : keyList){
				map.put(key, (String) obj.get(key));
			}
			
			returnList.add(map);
		}
		
		return returnList;
		
	}
	
	
	
	public static Map<String, String> getMapFromJsonString(String notParseJsonString){
        JSONObject jsonObject = parseJsonFromString(notParseJsonString);
        return getMapFromJson(jsonObject);
	}
	
	
	public static List<Map<String, String>> getListMapFromJsonString(String notParseJsonString){
		
		JSONArray jsonArray = parseJsonArrayFromString(notParseJsonString);
		int sizeOfArray = jsonArray.size();
		
		List<Map<String, String>> returnList = new ArrayList<>(sizeOfArray);
		
		for(int i = 0; i < sizeOfArray; i++){
			JSONObject tempJsonObject = (JSONObject) jsonArray.get(i);
			Map<String, String> tempMap = getMapFromJson(tempJsonObject);
			
			returnList.add(tempMap);
		}
		
		return returnList;
	}
	
	
	
	private static Map<String, String> getMapFromJson(JSONObject obj){
		
		Set<String> keySet = obj.keySet();

		Map<String, String> returnMap = new HashMap<>();
		
		for(String key : keySet){
			returnMap.put(key, (String) obj.get(key));
		}
		
		return returnMap;
	}
	
	private static JSONObject parseJsonFromString(String notParseJsonString){
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONObject jsonObject = (JSONObject) parser.parse(notParseJsonString);
			return jsonObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static JSONArray parseJsonArrayFromString(String notParseJsonString){
		JSONParser parser = new JSONParser();
		
		try {
			
			JSONArray jsonObject = (JSONArray) parser.parse(notParseJsonString);
			return jsonObject;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
