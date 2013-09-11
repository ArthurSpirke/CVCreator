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

import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.ImageSize;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CertificateServiceTest {

	private static List<Map<String, String>> list = null;
	private static Map<String, String> map = null;
	private static FactoryService<Certificate> certificateFactoryService = null;
	private static DBService<Certificate> certificateDBService = null;
	
	@BeforeClass
	public static void init(){
		list = new ArrayList<>();
		map = new HashMap<>();
		
		map.put("certId", "11");
		map.put("certSize", "300x400");
		map.put("pathToImages", "http://rrrrrr.com/images.jpg");
		map.put("certSmallDesc", "Small desc");
		map.put("certFullDesc", "Full desc");
		map.put("certState", "new");
		
		list.add(map);
		
		map.put("certId", "14");
		map.put("certSize", "300x400");
		map.put("pathToImages", "http://rrrrrr.com/images.jpg");
		map.put("certSmallDesc", "Small desc 2");
		map.put("certFullDesc", "Full desc 2");
		map.put("certState", "new");
		
		list.add(map);
		
		certificateFactoryService = FactoryServiceFactory.getFactoryService(EntityType.CERTIFICATE);
		certificateDBService = DBServiceFactory.getDBService(EntityType.CERTIFICATE);
	}
	
	@Test
	public void testGetEntityFactoryServiceMethod(){
		Certificate cert = certificateFactoryService.getEntity("177", map);
		
		assertThat(cert.getId(), is(equalTo("14")));
		assertThat(cert.getPersonId(), is(equalTo("177")));
		assertThat(cert.getImageSize(), is(equalTo(ImageSize._300x400)));
		//assertThat(cert.getImages(), is(equalTo()));
		assertThat(cert.getSmallDescription(), is(equalTo("Small desc 2")));
		assertThat(cert.getFullDescription(), is(equalTo("Full desc 2")));
		assertThat(cert.getState(), is(equalTo("new")));
	}
	
	@Test
	public void testGetEntitiesFactoryServiceMethod(){
		List<Certificate> certificates = certificateFactoryService.getEntitiesList(2, "189", list);
		
		assertThat(certificates.size(), is(equalTo(2)));
	}
	
	@Test
	public void testGetEntityFactoryServiceNewCertificate(){
		Map<String, String> internalMap = new HashMap<>();
		
		internalMap.put("certId", "0");
		internalMap.put("certSize", "300x400");
		internalMap.put("pathToImages", "http://rrrrrr.com/images.jpg");
		internalMap.put("certSmallDesc", "Small desc");
		internalMap.put("certFullDesc", "Full desc");
		internalMap.put("certState", "new");
		
		Certificate certificate = certificateFactoryService.getEntity("17", internalMap);
		
		assertThat(certificate.getId(), is(not("0")));
		//id of Component must be 36 character length
		assertThat(certificate.getId().length(), is(equalTo(36)));
	}
}
