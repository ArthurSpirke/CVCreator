package com.arthurspirke.cvcreator.filtres;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.util.JSONUtil;

import static com.arthurspirke.cvcreator.util.Utils.*;


public class SelectLocaleChangesFilter implements Filter {
 Logger log = Logger.getLogger(SelectLocaleChangesFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         
		request.setCharacterEncoding("UTF-8");
		
		BufferedReader bf = request.getReader();
		JSONObject obj = JSONUtil.getJSONObject(bf);
		
		List<Integer> allCheckIds = new ArrayList<>();
		
		allCheckIds.addAll(getListOfIntegers(obj.get("personCountryArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("personRegionArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("personCityArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("eduCountryArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("eduRegionArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("eduCityArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("empCountryArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("empRegionArrayId")));
		allCheckIds.addAll(getListOfIntegers(obj.get("empCityArrayId")));
		
		log.debug("Check array - " + Arrays.asList(allCheckIds));
		
		List<String> idMarks = new ArrayList<>();
		
		idMarks.addAll((List<String>) obj.get("personCountryArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("personRegionArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("personCityArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("eduCountryArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("eduRegionArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("eduCityArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("empCountryArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("empRegionArrayIdMark"));
		idMarks.addAll((List<String>) obj.get("empCityArrayIdMark"));
		
		log.debug("Check an array - " + idMarks);


		
		request.setAttribute("selectIds", JSONUtil.getStringList(obj.get("arrOfSelectIds")));
		request.setAttribute("allCheckIds", allCheckIds);
		request.setAttribute("allTypes", JSONUtil.getStringList(obj.get("arrOfTypes")));
		request.setAttribute("idMarks", idMarks);
		request.setAttribute("prefLang", obj.get("prefLang"));
		
		
		
		
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		
	}

	
}
