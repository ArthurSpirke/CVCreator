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
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EducationType;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class EducationServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<Education> eduFactoryService = null;
	private static DBService<Education> eduDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("eduId", "11");
		map.put("eduType", "University");
		map.put("eduTitle", "MIT");
		map.put("eduYears", "2008-2013");
		map.put("eduDegree", "Master of CS");
		map.put("eduDesription", "My Desc");
		map.put("eduState", "new");
		map.put("id", "17");
		map.put("prefLang", "English");
		map.put("countryId", "165");
		map.put("regionId", "1153");
		map.put("cityId", "11462");
		map.put("state", "new");


		
		list.add(map);
		
		map.put("eduId", "16");
		map.put("eduType", "College");
		map.put("eduTitle", "Harry Stock College");
		map.put("eduYears", "2008-2013");
		map.put("eduDegree", "Bachelor");
		map.put("eduDescription", "My Desc 2");
		map.put("eduState", "new");
		map.put("id", "30");
		map.put("prefLang", "English");
		map.put("countryId", "187");
		map.put("regionId", "1456");
		map.put("cityId", "12356");
		map.put("state", "new");

		
		list.add(map);
		
		eduFactoryService = FactoryServiceFactory.getFactoryService(EntityType.EDUCATION);
		eduDBService = DBServiceFactory.getDBService(EntityType.EDUCATION);
		
	}
	
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		Education education = eduFactoryService.getEntity("145", map);
		
		assertThat(education.getId(), is(equalTo("16")));
		assertThat(education.getType(), is(equalTo(EducationType.COLLEGE)));
		assertThat(education.getTitle(), is(equalTo("Harry Stock College")));
		assertThat(education.getYears(), is(equalTo("2008-2013")));
		assertThat(education.getDegree(), is(equalTo("Bachelor")));
		assertThat(education.getDescription(), is(equalTo("My Desc 2")));
		assertThat(education.getState(), is(equalTo("new")));
		
		Address eduAddress = education.getAddress();
		
		assertThat(eduAddress.getCountryId(), is(equalTo(187)));
		assertThat(eduAddress.getRegionId(), is(equalTo(1456)));
		assertThat(eduAddress.getCityId(), is(equalTo(12356)));
	}
	
	
	@Test
	public void testGetEntitiesFactoryServiceMethod(){
		List<Education> educations = eduFactoryService.getEntitiesList(2, "235", list);
		
		assertThat(educations.size(), is(equalTo(2)));
	}
	
	@Test
	public void testGetEntityFactoryServiceNewEducation(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("eduId", "0");
		internalMap.put("eduType", "University");
		internalMap.put("eduTitle", "MIT");
		internalMap.put("eduYears", "2008-2013");
		internalMap.put("eduDegree", "Master of CS");
		internalMap.put("eduDesription", "My Desc");
		internalMap.put("eduState", "new");
		internalMap.put("id", "19");
		internalMap.put("prefLang", "English");
		internalMap.put("countryId", "165");
		internalMap.put("regionId", "1153");
		internalMap.put("cityId", "11462");
		internalMap.put("state", "new");
		//internalMap.put("parentEntityId", "11");
		
		Education education = eduFactoryService.getEntity("117", internalMap);
		
		assertThat(education.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(education.getId().length(), is(equalTo(36)));
	}
	
}
