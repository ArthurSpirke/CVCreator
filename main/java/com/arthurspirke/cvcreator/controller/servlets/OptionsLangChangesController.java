package com.arthurspirke.cvcreator.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.service.JSONGenerator;
import com.arthurspirke.cvcreator.service.JSONParse;
import com.arthurspirke.cvcreator.util.AppProperties;
import com.arthurspirke.cvcreator.util.JSONUtil;
import com.arthurspirke.cvcreator.util.ServletUtils;


public class OptionsLangChangesController extends HttpServlet {
 private Logger log = Logger.getLogger(OptionsLangChangesController.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		 JSONObject mainJsonObject = JSONUtil.getJSONObject(request.getReader());
         JSONArray requestJsonArray = (JSONArray) mainJsonObject.get("list");
         Language lang  = Language.getLanguage((String) mainJsonObject.get("lang"));
         
         JSONArray responseArray = JSONGenerator.getJSONArrayFromPlacesData(JSONParse.getMapFromJsonArray(requestJsonArray, AppProperties.getPlacesIdNames()), lang);
     
		
         log.debug("Response JSON Array of places lists - " + responseArray.toJSONString()); 
         ServletUtils.setHeaders(response, "application/json");
         response.getWriter().print(responseArray);

	}


}
