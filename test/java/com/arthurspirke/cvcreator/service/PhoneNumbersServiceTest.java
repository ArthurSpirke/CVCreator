package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;

import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;


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

import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;
import com.arthurspirke.cvcreator.entity.enums.TemplateDOC;
import com.arthurspirke.cvcreator.entity.enums.TemplateHTML;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class PhoneNumbersServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<PhoneNumbers> phoneNumbersFactoryService = null;
	private static DBService<PhoneNumbers> phoneNumbersDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("phId", "11");
		map.put("phoneType", "Home");
		map.put("number", "+198735626");
		map.put("phState", "new");
		
		list.add(map);
		
		map.put("phId", "5");
		map.put("phoneType", "Home");
		map.put("number", "+198735626");
		map.put("phState", "new");
		
		list.add(map);
		
		phoneNumbersFactoryService = FactoryServiceFactory.getFactoryService(EntityType.PHONE_NUMBERS);
		phoneNumbersDBService = DBServiceFactory.getDBService(EntityType.PHONE_NUMBERS);
	}
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		PhoneNumbers phoneNumber = phoneNumbersFactoryService.getEntity("117", map);
		
		assertThat(phoneNumber.getId(), is(equalTo("5")));
		assertThat(phoneNumber.getPersonId(), is(equalTo("117")));
		assertThat(phoneNumber.getPhoneType(), is(equalTo(PhoneIcon.HOME_PHONE)));
		assertThat(phoneNumber.getPhoneNumber(), is(equalTo("+198735626")));
		assertThat(phoneNumber.getState(), is(equalTo("new")));
	}
	
	@Test
	public void testGetEntitiesFactoryService(){
		List<PhoneNumbers> phoneNumbers = phoneNumbersFactoryService.getEntitiesList(2, "119", list);
		
		assertThat(phoneNumbers.size(), is(equalTo(2)));
	}
	
	@Test
	public void testGetEntityFactoryServiceNewPhoneNumbers(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("phId", "0");
		internalMap.put("phoneType", "Home");
		internalMap.put("number", "+198735626");
		internalMap.put("phState", "new");
		
		PhoneNumbers phoneNumber = phoneNumbersFactoryService.getEntity("117", internalMap);
		
		assertThat(phoneNumber.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(phoneNumber.getId().length(), is(equalTo(36)));
	}
	
	
}
