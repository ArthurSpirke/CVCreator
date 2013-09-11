package com.arthurspirke.cvcreator.controller.filters;

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

import com.arthurspirke.cvcreator.util.JSONUtil;


public class GenerateResumeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        
        BufferedReader bf = request.getReader();
  		JSONObject jsonObject = getJSONObject(bf);
  		Map<String, List<Map<String, String>>> additionInfo = new HashMap<>();

  		additionInfo.put("phoneNumbers", getMapOnListInfo(jsonObject.get("phoneNumbers"), getPhoneNumbersKeys()));
  		additionInfo.put("address", getMapOnListInfo(jsonObject.get("address"), getAddressKeys()));
  		additionInfo.put("personalInfo", getMapOnListInfo(jsonObject.get("personalInfo"), getPersonalInfoKeys()));
  		additionInfo.put("personalTemplates", getMapOnListInfo(jsonObject.get("personalTemplates"), getPersonTemplatesKeys()));
  		additionInfo.put("personLinks", getMapOnListInfo(jsonObject.get("personLinks"), getPersonLinksKeys()));
  		additionInfo.put("skills", getMapOnListInfo(jsonObject.get("skills"), getSkillsKeys()));
        additionInfo.put("education", getMapOnListInfo(jsonObject.get("education"), getEducationKeys()));
        additionInfo.put("employmentHistory", JSONUtil.special(jsonObject.get("employmentHistory")));
        additionInfo.put("certificate", getMapOnListInfo(jsonObject.get("certificate"), getCertificateKeys()));
      
        request.setAttribute("operationType", jsonObject.get("operationType"));
  		request.setAttribute("person", getMapInfo(jsonObject.get("person"), getPersonKeys()));
  		request.setAttribute("additionInfo", additionInfo);
  		
  		chain.doFilter(request, response);
	
	}


	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}

}
