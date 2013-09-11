package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;

import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.business.Project;


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

public class ProjectServiceTest {

		private static List<Map<String, String>> list = null;
		private static Map<String, String> map = null;
		private static FactoryService<Project> projectFactoryService = null;
		private static DBService<Project> projectDBService = null;
		
		@BeforeClass
		public static void init(){
			list = new ArrayList<>();
			map = new HashMap<>();
			
			map.put("projId", "11");
			map.put("companyId", "17");
			map.put("projTitle", "Google");
			map.put("projPosition", "Java Programmer");
			map.put("projYears", "2009-2013");
			map.put("projDescription", "My Desc");
			map.put("companyLink", "compLink17");
			map.put("projState", "new");
			
			list.add(map);
			
			map.put("projId", "17");
			map.put("companyId", "19");
			map.put("projTitle", "Rad Hat");
			map.put("projPosition", "Java Programmer");
			map.put("projYears", "2006-2013");
			map.put("projDescription", "My Desc 2");
			map.put("companyLink", "compLink19");
			map.put("projState", "new");
			
			list.add(map);
			
			projectFactoryService = FactoryServiceFactory.getFactoryService(EntityType.PROJECT);
			projectDBService = DBServiceFactory.getDBService(EntityType.PROJECT);
		}
		
		@Test
		public void testGetEntityFactoryServiceMethod(){
			Project project = projectFactoryService.getEntity("11", map);
			
			assertThat(project.getId(), is(equalTo("17")));
			assertThat(project.getPersonId(), is(equalTo("11")));
			assertThat(project.getCompanyId(), is(equalTo("19")));
			assertThat(project.getTitle(), is(equalTo("Rad Hat")));
			assertThat(project.getPosition(), is(equalTo("Java Programmer")));
			assertThat(project.getYears(), is(equalTo("2006-2013")));
			assertThat(project.getDescription(), is(equalTo("My Desc 2")));
			assertThat(project.getCompanyLink(), is(equalTo("compLink19")));
			assertThat(project.getState(), is(equalTo("new")));
			
		}
		
		@Test
		public void testGetEntitiesFactoryServiceMethod(){
			List<Project> projectList = projectFactoryService.getEntitiesList(2, "119", list);
			
			assertThat(projectList.size(), is(equalTo(2)));
		}
		
		@Test
		public void testGetEntityFactoryServiceNewProject(){
			Map<String, String> internalMap = new HashMap<>();
			
			internalMap.put("projId", "0");
			internalMap.put("companyId", "17");
			internalMap.put("projTitle", "Google");
			internalMap.put("projPosition", "Java Programmer");
			internalMap.put("projYears", "2009-2013");
			internalMap.put("projDescription", "My Desc");
			internalMap.put("companyLink", "compLink17");
			internalMap.put("projState", "new");
			
			Project project = projectFactoryService.getEntity("117", internalMap);
			
			assertThat(project.getId(), is(not("0")));
			//id of Component must be 36 character length
			assertThat(project.getId().length(), is(equalTo(36)));
		}
		
		
		
}
