package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class EmploymentHistoryServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<EmploymentHistory> empFactoryService = null;
	private static DBService<EmploymentHistory> empDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
        map.put("empMainInfo", "{\"empId\": \"0\", \"empTitle\": \"Google\", \"empYears\": \"2008-2013\", \"empPosition\": \"Java Programmer\", \"empDescription\": \"Love this!\", \"empState\": \"new\", \"projectCount\": \"1\"}");
        map.put("empAddress", "{\"id\":\"0\", \"countryId\": \"121\",\"regionId\": \"11\", \"cityId\": \"423\", \"prefLang\": \"English\"}");
        map.put("projects", "[{\"projId\": \"0\", \"companyId\": \"0\", \"projTitle\": \"MyProject\", \"projPosition\": \"Java Programmer\", \"projYears\": \"2008-2013\", \"projDescription\": \"Love this!\", \"projState\": \"new\"}]");
		
        list.add(map);
        
        map.put("empMainInfo", "{\"empId\": \"15\", \"empTitle\": \"Google\", \"empYears\": \"2009-2013\", \"empPosition\": \"Java Programmer\", \"empDescription\": \"Love this!\", \"empState\": \"new\", \"projectCount\": \"1\"}");
        map.put("empAddress", "{\"id\":\"0\", \"countryId\": \"121\",\"regionId\": \"11\", \"cityId\": \"423\", \"prefLang\": \"English\"}");
        map.put("projects", "[{\"projId\": \"19\", \"companyId\": \"15\", \"projTitle\": \"MyProject\", \"projPosition\": \"Java Programmer\", \"projYears\": \"2008-2013\", \"projDescription\": \"Love this!\", \"projState\": \"new\"}]");
		
		list.add(map);
		
		empFactoryService = FactoryServiceFactory.getFactoryService(EntityType.EMPLOYMENT_HISTORY);
		empDBService = DBServiceFactory.getDBService(EntityType.EMPLOYMENT_HISTORY);
	}
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		EmploymentHistory empHis = empFactoryService.getEntity("177", map);
		
		assertThat(empHis.getId(), is(equalTo("15")));
		assertThat(empHis.getPersonId(), is(equalTo("177")));
		assertThat(empHis.getTitle(), is(equalTo("Google")));
		assertThat(empHis.getYears(), is(equalTo("2009-2013")));
		assertThat(empHis.getPosition(), is(equalTo("Java Programmer")));
		assertThat(empHis.getDescription(), is(equalTo("Love this!")));
		assertThat(empHis.getState(), is(equalTo("new")));
		
		Address empAddress = empHis.getAddress();
		
		assertThat(empAddress.getCountryId(), is(equalTo(121)));
		assertThat(empAddress.getRegionId(), is(equalTo(11)));
		assertThat(empAddress.getCityId(), is(equalTo(423)));
		
		Project empProject = empHis.getProjects().get(0);
		
		assertThat(empProject.getId(), is(equalTo("19")));
		assertThat(empProject.getHostId(), is(equalTo("15")));
		assertThat(empProject.getTitle(), is(equalTo("MyProject")));
		assertThat(empProject.getPosition(), is(equalTo("Java Programmer")));
		assertThat(empProject.getYears(), is(equalTo("2008-2013")));
		assertThat(empProject.getDescription(), is(equalTo("Love this!")));
		assertThat(empProject.getState(), is(equalTo("new")));
	}
	
	@Test
	public void testGetEntitiesFactoryServiceMethod(){
		List<EmploymentHistory> empHistoryList = empFactoryService.getEntitiesList(2, "133", list);
		
		assertThat(empHistoryList.size(), is(equalTo(2)));
	}
	
}
