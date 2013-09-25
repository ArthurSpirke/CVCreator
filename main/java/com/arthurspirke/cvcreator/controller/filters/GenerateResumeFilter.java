package com.arthurspirke.cvcreator.controller.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.service.JSONGenerator;
import static com.arthurspirke.cvcreator.service.JSONParse.*;


public class GenerateResumeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        
        BufferedReader bf = request.getReader();
  		JSONObject jsonObject = JSONGenerator.getJsonObject(bf);
  		Map<String, List<Map<String, String>>> additionInfo = new HashMap<>();

  		additionInfo.put("phoneNumbers", getListMapFromJson(jsonObject.get("phoneNumbers")));
  		additionInfo.put("address", getListMapFromJson(jsonObject.get("address")));
  		additionInfo.put("personalInfo", getListMapFromJson(jsonObject.get("personalInfo")));
  		additionInfo.put("personalTemplates", getListMapFromJson(jsonObject.get("personalTemplates")));
  		additionInfo.put("personLinks", getListMapFromJson(jsonObject.get("personLinks")));
  		additionInfo.put("skills", getListMapFromJson(jsonObject.get("skills")));
        additionInfo.put("education", getListMapFromJson(jsonObject.get("education")));
        additionInfo.put("employmentHistory", getListMapForEmpHistory(jsonObject.get("employmentHistory")));
        additionInfo.put("certificate", getListMapFromJson(jsonObject.get("certificate")));
      
        request.setAttribute("operationType", jsonObject.get("operationType"));
  		request.setAttribute("person", getMapFromJson(jsonObject.get("person")));
  		request.setAttribute("additionInfo", additionInfo);
  		
  		chain.doFilter(request, response);
	
	}


	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}

}
