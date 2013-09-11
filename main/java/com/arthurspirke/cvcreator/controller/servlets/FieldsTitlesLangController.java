package com.arthurspirke.cvcreator.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.service.JSONGenerator;
import com.arthurspirke.cvcreator.service.StaticInfo;
import com.arthurspirke.cvcreator.util.ServletUtils;
import com.arthurspirke.cvcreator.util.Utils;


public class FieldsTitlesLangController extends HttpServlet {
	private Logger log = Logger.getLogger(FieldsTitlesLangController.class); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Language lang = Language.getLanguage(request.getParameter("lang"));
		String type = request.getParameter("type");
        ResourceBundle res = Utils.getResourceBundleByLang(lang);
        
		StaticInfo s = new StaticInfo(lang);
		JSONObject json = null;
		JSONGenerator jsonGenerator = new JSONGenerator();
		
        if("simple_static".equals(type)){
            json = jsonGenerator.getJsonObjectLocaleFields(s.getSimpleStaticInfo());
            json.putAll(jsonGenerator.getJsonObjectLocaleArrayFields(s.getArrayStaticInfo()));
            json.putAll(jsonGenerator.getJsonObjectLocaleCountryList(s.getCountryStaticInfo()));
            json.put("placeOptionFirstElement", res.getString("select_country"));
            json.put("haveFieldsData", true);
        } else if("error_static".equals(type)){
            json = new JSONGenerator().getJsonObjectLocaleErros(s.getErrorStatcInfo());
            json.put("haveErrorData", true);
        } else {
        	
        }
        
        json.put("currentLang", lang.toString());
        
        log.debug(json.toJSONString());
        
        ServletUtils.setHeaders(response, "application/json");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		
		
		
	}


}
