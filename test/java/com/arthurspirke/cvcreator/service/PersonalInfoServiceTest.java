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
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PersonalInfoServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<PersonalInfo> personalInfoFactoryService = null;
	private static DBService<PersonalInfo> personalInfoDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("id", "17");
		map.put("firstName", "John");
		map.put("lastName", "Smith");
		map.put("claimPosition", "Java Programmer");
		map.put("eMail", "java.programmer@gmail.com");
		map.put("profile", "My Profile");
		map.put("hobbies", "My hobbies");
		map.put("state", "new");
		
		list.add(map);
		
		map.put("id", "19");
		map.put("firstName", "Arthur");
		map.put("lastName", "Smith");
		map.put("claimPosition", "Java Programmer");
		map.put("eMail", "java.programmer@gmail.com");
		map.put("profile", "My Profile 2");
		map.put("hobbies", "My hobbies 2");
		map.put("state", "new");
		
		list.add(map);
		
		personalInfoFactoryService = FactoryServiceFactory.getFactoryService(EntityType.PERSONAL_INFO);
		personalInfoDBService = DBServiceFactory.getDBService(EntityType.PERSONAL_INFO);
	}
	
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		PersonalInfo personalInfo = personalInfoFactoryService.getEntity("117", map);
		
		assertThat(personalInfo.getId(), is(equalTo("19")));
		assertThat(personalInfo.getPersonId(), is(equalTo("117")));
		assertThat(personalInfo.getFirstName(), is(equalTo("Arthur")));
		assertThat(personalInfo.getSecondName(), is(equalTo("Smith")));
		assertThat(personalInfo.getClaimPosition(), is(equalTo("Java Programmer")));
		assertThat(personalInfo.geteMail(), is(equalTo("java.programmer@gmail.com")));
		assertThat(personalInfo.getProfile(), is(equalTo("My Profile 2")));
		assertThat(personalInfo.getHobbies(), is(equalTo("My hobbies 2")));
		assertThat(personalInfo.getState(), is(equalTo("new")));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetEntitiesFactoryServiceMethod(){
		List<PersonalInfo> personalInfoList = personalInfoFactoryService.getEntitiesList(2, "111", list);
	}
	
	@Test
	public void testGetEntityFactoryServiceNewPersonalInfo(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("id", "0");
		internalMap.put("firstName", "John");
		internalMap.put("lastName", "Smith");
		internalMap.put("claimPosition", "Java Programmer");
		internalMap.put("eMail", "java.programmer@gmail.com");
		internalMap.put("profile", "My Profile");
		internalMap.put("hobbies", "My hobbies");
		internalMap.put("state", "new");
		
		PersonalInfo personalInfo = personalInfoFactoryService.getEntity("118", internalMap);
		
		assertThat(personalInfo.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(personalInfo.getId().length(), is(equalTo(36)));
	}
}
