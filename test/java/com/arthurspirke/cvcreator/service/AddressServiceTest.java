package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;

import org.junit.Before;
import org.junit.Test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import com.arthurspirke.cvcreator.entity.business.Skills;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

public class AddressServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<Address> addressFactoryService = null;
	private static DBService<Address> addressDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("id", "19");
		map.put("countryId", "26");
		map.put("regionId", "14");
		map.put("cityId", "255");
		map.put("postalCode", "123456");
		map.put("streetAddress", "Main Street");
		map.put("prefLang", "English");
		map.put("state", "new");
		
		list.add(map);
		
		map.put("id", "11");
		map.put("countryId", "135");
		map.put("regionId", "356");
		map.put("cityId", "11235");
		map.put("postalCode", "345123");
		map.put("streetAddress", "Town Street");
		map.put("prefLang", "English");
		map.put("state", "new");
		
		list.add(map);
		
		addressFactoryService = FactoryServiceFactory.getFactoryService(EntityType.ADDRESS);
		addressDBService = DBServiceFactory.getDBService(EntityType.ADDRESS);
	}
	
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		Address address = addressFactoryService.getEntity("117", map);
		
		assertThat(address.getId(), is(equalTo("11")));
		assertThat(address.getPersonId(), is(equalTo("117")));
		assertThat(address.getCountryId(), is(equalTo(135)));
		assertThat(address.getRegionId(), is(equalTo(356)));
		assertThat(address.getPostalCode(), is(equalTo(345123)));
		assertThat(address.getStreet(), is(equalTo("Town Street")));
		assertThat(address.getState(), is(equalTo("new")));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetEntitiesFactoryServiceMethod(){
		List<Address> address = addressFactoryService.getEntitiesList(2, "199", list);
		
		assertThat(address.size(), is(equalTo(2)));
	}
	
}
