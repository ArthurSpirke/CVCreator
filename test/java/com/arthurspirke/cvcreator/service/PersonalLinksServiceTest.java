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
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.LinkIcon;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class PersonalLinksServiceTest {
	
	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<PersonLinks> linkFactoryService = null;
	private static DBService<PersonLinks> linkDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("linkId", "112");
		map.put("linkType", "My link");
		map.put("link", "http://link.com");
		map.put("linkState", "new");
		
		list.add(map);
		
		map.put("linkId", "105");
		map.put("linkType", "My link");
		map.put("link", "http://links123.com");
		map.put("linkState", "new");
		
		list.add(map);
		
		linkFactoryService = FactoryServiceFactory.getFactoryService(EntityType.PERSONAL_LINKS);
		linkDBService = DBServiceFactory.getDBService(EntityType.PERSONAL_LINKS);
	}
	

	@Test
	public void testGetEntityFactoryServiceMethod(){
		PersonLinks link = linkFactoryService.getEntity("11", map);
		
		assertThat(link.getId(), is(equalTo("105")));
		assertThat(link.getPersonId(), is(equalTo("11")));
		assertThat(link.getLinkType(), is(LinkIcon.MY_LINK));
		assertThat(link.getLinkName(), is(equalTo("http://links123.com")));
		assertThat(link.getState(), is(equalTo("new")));
	}
	
	@Test
	public void testGetEntitiesFactoryServiceMethod(){
		List<PersonLinks> personLinks = linkFactoryService.getEntitiesList(2, "100", list);
		
		assertThat(personLinks.size(), is(equalTo(2)));
	}
	
	@Test
	public void testGetEntityFactoryServiceNewPersonalLink(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("linkId", "0");
		internalMap.put("linkType", "My link");
		internalMap.put("link", "http://links123.com");
		internalMap.put("linkState", "new");
		
		PersonLinks personLink = linkFactoryService.getEntity("117", internalMap);
		
		assertThat(personLink.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(personLink.getId().length(), is(equalTo(36)));
	}
}
