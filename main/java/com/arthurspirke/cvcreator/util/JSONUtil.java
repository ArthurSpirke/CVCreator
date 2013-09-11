package com.arthurspirke.cvcreator.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONUtil {
	private static Logger log = Logger.getLogger(JSONUtil.class);

	public static JSONObject getJSONObject(BufferedReader bf) {
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
	
	public static JSONObject getJSONObject(String jsonRequest) {
	     JSONParser parser = new JSONParser();
	     JSONObject o = null;
	try {
		o = (JSONObject) parser.parse(jsonRequest);

	} catch (ParseException e) {
		log.error("Error - " + e);
	}

	return o;
}
	
	public static JSONArray getJSONArray(String jsonRequest) {
	     JSONParser parser = new JSONParser();
	     JSONArray o = null;
	try {
		o = (JSONArray) parser.parse(jsonRequest);

	} catch (ParseException e) {
		log.error("Error - " + e);
	}

	return o;
}
	
	public static List<String> getStringList(Object obj){
		List<String> result = new ArrayList<>();
		JSONArray array = (JSONArray) obj;
		
		for(int i = 0; i < array.size(); i++){
			result.add((String) array.get(i));
		}
		return result;
	}
	
	
	
	public static Map<String, String> getMapInfo(Object json, String[] keys){
		Map<String, String> returnMap = new HashMap<>();
		JSONObject obj = (JSONObject) json;
		
		for(int i = 0; i < keys.length; i++){
			returnMap.put(keys[i], (String) obj.get(keys[i]));
		}
		
		return returnMap;
	}
	
	
	public static List<Map<String, String>> getMapOnListInfo(Object jsonArray, String[] keys){
		      List<Map<String, String>> returnList = new ArrayList<>();
              JSONArray array = (JSONArray) jsonArray;
              JSONObject obj = null;
              HashMap<String, String> map = null;

              for(int i = 0; i < array.size(); i++){
                   obj = (JSONObject)array.get(i);
                   map = new HashMap<>();
                      for(int j = 0; j < keys.length; j++){
                    	  map.put(keys[j], (String)obj.get(keys[j]));
                      }
            	  returnList.add(map);
              }
              
              return returnList;
	}
	

	public static List<Map<String, String>> modifyProjectMapInfo(List<Map<String, String>> oldProjectsInfo, String companyId){
		
		for(Map<String, String> map : oldProjectsInfo){
			map.put("companyId", companyId);
		}
		
      	return oldProjectsInfo;
	}
	
	public static List<Map<String, String>> special(Object obj){
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
	


}
