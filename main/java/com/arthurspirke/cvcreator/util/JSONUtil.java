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
		
		//TODO: bad implementation!
		if("0".equals((String)obj.get("personId"))){
			String personId = String.valueOf(Utils.getUniqueUserId(returnMap.get("firstName"), returnMap.get("lastName")));
			returnMap.put("personId", personId);
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
	



}
