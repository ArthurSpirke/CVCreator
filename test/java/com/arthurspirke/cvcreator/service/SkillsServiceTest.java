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

import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SkillsServiceTest {
	
	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<Skills> skillsFactoryService = null;
	private static DBService<Skills> skillsDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("skillsId", "11");
		map.put("skillsName", "Language");
		map.put("skillsValue", "Java, JavaScript, Scala");
		map.put("skillsState", "new");
		
		list.add(map);
		
		map.put("skillsId", "17");
		map.put("skillsName", "RDBMS");
		map.put("skillsValue", "MySQL, MariaDB, MongoDB, PostgreSQL, Oracle");
		map.put("skillsState", "new");
		
		list.add(map);
		
		skillsFactoryService = FactoryServiceFactory.getFactoryService(EntityType.SKILLS);
		skillsDBService = DBServiceFactory.getDBService(EntityType.SKILLS);
	}
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		Skills skills = skillsFactoryService.getEntity("15", map);
		
		assertThat(skills.getId(), is(equalTo("17")));
		assertThat(skills.getPersonId(), is(equalTo("15")));
		assertThat(skills.getSkillsName(), is(equalTo("RDBMS")));
		assertThat(skills.getSkillsValue(), is(equalTo("MySQL, MariaDB, MongoDB, PostgreSQL, Oracle")));
		assertThat(skills.getState(), is(equalTo("new")));
	}
	
	@Test
	public void testGetEntitiesFactoryServiceMethod(){
		List<Skills> skillsList = skillsFactoryService.getEntitiesList(2, "19", list);
		
		assertThat(skillsList.size(), is(equalTo(2)));
	}
	
	@Test
	public void testGetEntityFactoryServiceCreateNewSkills(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("skillsId", "0");
		internalMap.put("skillsName", "RDBMS");
		internalMap.put("skillsValue", "MySQL, MariaDB, MongoDB, PostgreSQL, Oracle");
		internalMap.put("skillsState", "new");
		
		Skills skills = skillsFactoryService.getEntity("19", internalMap);
		
		assertThat(skills.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(skills.getId().length(), is(equalTo(36)));
	}
}
