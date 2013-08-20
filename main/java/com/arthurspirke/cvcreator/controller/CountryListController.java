package com.arthurspirke.cvcreator.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.JSONGenerator;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entityfactory.PlacesFactory;
import com.arthurspirke.cvcreator.util.ServletUtils;
import com.arthurspirke.cvcreator.util.Utils;

public class CountryListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String type = request.getParameter("type");
		  Language lang = Language.getLanguage(request.getParameter("selectLang"));
		  Integer placeId = Utils.getInteger(request.getParameter("id"));
		  ResourceBundle res = Utils.getResourceBundleByLang(lang);
		  
		  JSONObject obj = new JSONObject();
		  
		  
		  if("country_c".equals(type)){ 
			  
			Map<Integer, String> countryMap = PlacesFactory.getMapPlacesByLang(PlacesFactory.getCountryMap(), lang);
		    obj.put("placesList", JSONGenerator.getJSONReflectionByMapIntegerString(countryMap));
		    obj.put("placeOptionFirstElement", res.getString("select_country"));
		  } else if("region_c".equals(type)){
			  
	        Map<Integer, String> regionMap = PlacesFactory.getMapPlacesByLang(PlacesFactory.getRegionMap(placeId), lang);
	        obj.put("placesList", JSONGenerator.getJSONReflectionByMapIntegerString(regionMap));
	        obj.put("placeOptionFirstElement", res.getString("select_region"));
		  } else if("city_c".equals(type)){
			  
			Map<Integer, String> cityMap = PlacesFactory.getMapPlacesByLang(PlacesFactory.getCityMap(placeId), lang);
			obj.put("placesList", JSONGenerator.getJSONReflectionByMapIntegerString(cityMap));
			obj.put("placeOptionFirstElement", res.getString("select_city"));
		  } else {
			  
		  }
		  

          ServletUtils.setHeaders(response, "application/json");
	      PrintWriter pw = response.getWriter();
	      pw.print(obj);
	}


}
