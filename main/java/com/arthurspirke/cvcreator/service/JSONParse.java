package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONParse {

	public static List<Map<String, String>> getMapFromJsonArray(JSONArray array, String[] keyList){
        List<Map<String, String>> returnList = new ArrayList<>();
		
		for(int i = 0; i < array.size(); i++){
			
			JSONObject obj = (JSONObject) array.get(i);
			Map<String, String> map = new HashMap<>();

			for(String key : keyList){
				map.put(key, (String) obj.get(key));
			}
			
			returnList.add(map);
		}
		
		return returnList;
		
	}
	
	
	
	public static Map<String, String> getMapFromJsonString(String notParseJsonString){
        JSONObject jsonObject = JSONGenerator.getJsonObject(notParseJsonString);
        return getMapFromJson(jsonObject);
	}
	
	
	public static List<Map<String, String>> getListMapFromJsonString(String notParseJsonString){
		
		JSONArray jsonArray = JSONGenerator.getJsonArray(notParseJsonString);
		int sizeOfArray = jsonArray.size();
		
		List<Map<String, String>> returnList = new ArrayList<>(sizeOfArray);
		
		for(int i = 0; i < sizeOfArray; i++){
			JSONObject tempJsonObject = (JSONObject) jsonArray.get(i);
			Map<String, String> tempMap = getMapFromJson(tempJsonObject);
			
			returnList.add(tempMap);
		}
		
		return returnList;
	}
	
	public static List<Map<String, String>> getListMapFromJson(Object jsonObject){
		JSONArray obj = (JSONArray) jsonObject;
        return getListMapFromJsonString(obj.toJSONString());
	}
	
	
	public static List<Map<String, String>> getListMapForEmpHistory(Object obj){
		JSONArray array = (JSONArray) obj;
		List<Map<String, String>> returnListMap = new ArrayList<>();
		
		for(int i = 0; i < array.size(); i++){
			JSONObject object = (JSONObject) array.get(i);
			String mainEmpInfo = ((JSONObject)object.get("empMainInfo")).toJSONString();
		    String empAddress = ((JSONObject)object.get("empAddress")).toJSONString();
		    String projects = ((JSONArray)object.get("projects")).toJSONString();
		    
		    Map<String, String> tempMap = new HashMap<>();
		    
		    tempMap.put("empMainInfo", mainEmpInfo);
		    tempMap.put("empAddress", empAddress);
		    tempMap.put("projects", projects);
		    
		    returnListMap.add(tempMap);
		}

		return returnListMap;
	}
	
	
	public static Map<String, String> getMapFromJson(Object object){
		JSONObject obj = (JSONObject) object;
		Set<String> keySet = obj.keySet();

		Map<String, String> returnMap = new HashMap<>();
		
		for(String key : keySet){
			returnMap.put(key, (String) obj.get(key));
		}
		
		return returnMap;
	}
	
	public static List<String> getStringList(Object obj){
		List<String> result = new ArrayList<>();
		JSONArray array = (JSONArray) obj;
		
		for(int i = 0; i < array.size(); i++){
			result.add((String) array.get(i));
		}
		return result;
	}

	
}
