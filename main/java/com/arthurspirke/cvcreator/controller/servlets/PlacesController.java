package com.arthurspirke.cvcreator.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.service.JSONGenerator;
import com.arthurspirke.cvcreator.service.PlacesService;
import com.arthurspirke.cvcreator.util.ServletUtils;
import com.arthurspirke.cvcreator.util.Utils;

public class PlacesController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String type = request.getParameter("type");
		  Language lang = Language.getLanguage(request.getParameter("selectLang"));
		  Integer placeId = Utils.getInteger(request.getParameter("id"));
		  ResourceBundle res = Utils.getResourceBundleByLang(lang);
		  
		  JSONObject obj = new JSONObject();
		  
		  PlacesService placesService = new PlacesService(lang);
		  
		  if("country_c".equals(type)){ 
			  
			Map<String, Integer> countryMap = placesService.getCountryIdNameMap();
		    obj.put("placesList", JSONGenerator.getJSONReflectionByMapIntegerString(countryMap));
		    obj.put("placeOptionFirstElement", res.getString("select_country"));
		    
		  } else if("region_c".equals(type)){
			  
	        Map<String, Integer> regionMap = placesService.getRegionIdNameMap(placeId);
	        obj.put("placesList", JSONGenerator.getJSONReflectionByMapIntegerString(regionMap));
	        obj.put("placeOptionFirstElement", res.getString("select_region"));
	        
		  } else if("city_c".equals(type)){
			  
			Map<String, Integer> cityMap = placesService.getCityIdNameMap(placeId);
			obj.put("placesList", JSONGenerator.getJSONReflectionByMapIntegerString(cityMap));
			obj.put("placeOptionFirstElement", res.getString("select_city"));
			
		  } else {
			  throw new IllegalArgumentException();
		  }
		  

          ServletUtils.setHeaders(response, "application/json");
	      PrintWriter pw = response.getWriter();
	      pw.print(obj);
	}


}
