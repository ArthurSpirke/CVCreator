package com.arthurspirke.cvcreator.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.support.Places;
import com.arthurspirke.cvcreator.util.Utils;

import static com.arthurspirke.cvcreator.util.AppProperties.*;
import static com.arthurspirke.cvcreator.util.Utils.*;

public class StaticInfo {

	private ResourceBundle res;
	private Language lang;
	
	public StaticInfo(Language lang){
		this.lang = lang;
		res = getResourceBundleByLang(lang);
	}
	
	
	public Map<String, String> getSimpleStaticInfo(){
		return getMapFromFieldsNames(getStaticFieldsNames());
	}
	
	public Map<String, List<String>> getArrayStaticInfo(){
		return getMapFromFieldsArraysNames(getStaticFieldsArraysNames());
	}
	
	public Map<Integer, String> getCountryStaticInfo(){
		Map<Integer, String> returnMap = getMapCountryListByLang();
		return returnMap;
	}
	
	public Map<String, String> getErrorStatcInfo(){
		return getMapFromFieldsNames(getErrorStaticFields());
	}
	
	
	private Map<Integer, String> getMapCountryListByLang(){
		PlacesService service = new PlacesService(lang);
        return service.getCountryIdNameMap();
	}
	
	private Map<String, String> getMapFromFieldsNames(List<String> fieldsNames){
		Map<String, String> returnMap = new HashMap<>();	
		for(String f : fieldsNames){
			returnMap.put(f, res.getString(Utils.replaceSharpToDollarSign(f)));
		}
		return returnMap;
	}
	
	private Map<String, List<String>> getMapFromFieldsArraysNames(List<String> fieldsNames){
		Map<String, List<String>> returnMap = new HashMap<>();
		for(String f : fieldsNames){
			returnMap.put(f, Arrays.asList(res.getString(f).split(",")));
		}
		return returnMap;
	}
	
	
}
