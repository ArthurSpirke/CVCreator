package com.arthurspirke.cvcreator.filtres;

import static com.arthurspirke.cvcreator.util.AppProperties.*;
import static com.arthurspirke.cvcreator.util.JSONUtil.*;

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


public class GenerateResumeFilter implements Filter {
	
	public void destroy() {}



	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        
        BufferedReader bf = request.getReader();
  		JSONObject jsonObject = getJSONObject(bf);
  		Map<String, List<Map<String, String>>> additionInfo = new HashMap<>();

  		additionInfo.put("phoneNumbers", getMapOnListInfo(jsonObject.get("phoneNumbers"), getPhoneNumbersKeys()));
  		additionInfo.put("personLinks", getMapOnListInfo(jsonObject.get("personLinks"), getPersonLinksKeys()));
  		additionInfo.put("skills", getMapOnListInfo(jsonObject.get("skills"), getSkillsKeys()));
        additionInfo.put("education", getMapOnListInfo(jsonObject.get("education"), getEducationKeys()));
        additionInfo.put("employment", getMapOnListInfo(jsonObject.get("employment"), getEmploymentKeys()));
        additionInfo.put("projects", getMapOnListInfo(jsonObject.get("projects"), getProjectKeys()));
        additionInfo.put("certificate", getMapOnListInfo(jsonObject.get("certificate"), getCertificateKeys()));

        //addition attr to person info - count of entity
        
        request.setAttribute("operationType", jsonObject.get("operationType"));
  		request.setAttribute("person", getMapInfo(jsonObject.get("person"), getPersonKeys()));
  		request.setAttribute("additionInfo", additionInfo);
  		


  		
  		chain.doFilter(request, response);
		
		
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
