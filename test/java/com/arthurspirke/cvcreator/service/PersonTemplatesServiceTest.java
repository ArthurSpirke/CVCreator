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

import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.TemplateDOC;
import com.arthurspirke.cvcreator.entity.enums.TemplateHTML;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;



public class PersonTemplatesServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<PersonalTemplates> personalTemplatesFactoryService = null;
	private static DBService<PersonalTemplates> personalTemplatesDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("id", "11");
		map.put("templatePDF", "SIMPLE_PDF");
		map.put("templateHTML", "SIMPLE_HTML");
		map.put("templateDOC", "SIMPLE_DOC");
		map.put("state", "new");
		
		list.add(map);
		
		map.put("id", "17");
		map.put("templatePDF", "EXTENDED_PDF");
		map.put("templateHTML", "EXTENDED_HTML");
		map.put("templateDOC", "EXTENDED_DOC");
		map.put("state", "new");
		
		list.add(map);
		
		personalTemplatesFactoryService = FactoryServiceFactory.getFactoryService(EntityType.PERSONAL_TEMPLATES);
		personalTemplatesDBService = DBServiceFactory.getDBService(EntityType.PERSONAL_TEMPLATES);
	}
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		PersonalTemplates template = personalTemplatesFactoryService.getEntity("117", map);
		
		assertThat(template.getId(), is(equalTo("17")));
		assertThat(template.getPersonId(), is(equalTo("117")));
		assertThat(template.getTemplatePDF(), is(equalTo(TemplatePDF.EXTENDED)));
		assertThat(template.getTemplateHTML(), is(equalTo(TemplateHTML.EXTENDED)));
		assertThat(template.getTemplateDOC(), is(equalTo(TemplateDOC.EXTENDED)));
		assertThat(template.getState(), is(equalTo("new")));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testGetEntetiesFactoryServiceMethod(){
		List<PersonalTemplates> templates = personalTemplatesFactoryService.getEntitiesList(2, "118", list);
	}
	
	@Test
	public void testGetEntityFactoryServicePersonTemplates(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("id", "0");
		internalMap.put("templatePDF", "SIMPLE_PDF");
		internalMap.put("templateHTML", "SIMPLE_HTML");
		internalMap.put("templateDOC", "SIMPLE_DOC");
		internalMap.put("state", "new");
		
		PersonalTemplates template = personalTemplatesFactoryService.getEntity("117", internalMap);
		
		assertThat(template.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(template.getId().length(), is(equalTo(36)));
	}
}
