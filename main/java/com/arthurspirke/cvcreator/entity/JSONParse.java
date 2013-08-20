package com.arthurspirke.cvcreator.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	
}
