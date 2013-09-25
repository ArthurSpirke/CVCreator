package com.arthurspirke.cvcreator;

import static com.arthurspirke.cvcreator.util.AppProperties.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.service.PersonResumeService;
import com.arthurspirke.cvcreator.util.AppProperties;

public class MainTest {
	/*private final static File FILE_WITH_JSON = new File("C:/Users/Arthur/Downloads/testJson.txt");
    private static String json = "";
	
	@BeforeClass
	public static void loadJson(){
		try {
			InputStream in = new FileInputStream(FILE_WITH_JSON);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			StringBuilder sb = new StringBuilder();
			
			String line = "";
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			json = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStandardJsonValid(){
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		assertThat(obj, is(notNullValue()));
	}
	
	

	
	@Test
	public void testPersonAsseblyFromJson(){
		JSONObject jsonObject = JSONUtil.getJSONObject(json);
		
  		Map<String, List<Map<String, String>>> additionInfo = new HashMap<>();
  		Map<String, String> personInfo = getMapInfo(jsonObject.get("person"), getPersonKeys());
  		
  		additionInfo.put("address", getMapOnListInfo(jsonObject.get("address"), getAddressKeys()));
  		additionInfo.put("personalInfo", getMapOnListInfo(jsonObject.get("personalInfo"), getPersonalInfoKeys()));
  		additionInfo.put("personalTemplates", getMapOnListInfo(jsonObject.get("personalTemplates"), getPersonTemplatesKeys()));
  		additionInfo.put("phoneNumbers", getMapOnListInfo(jsonObject.get("phoneNumbers"), getPhoneNumbersKeys()));
  		additionInfo.put("personLinks", getMapOnListInfo(jsonObject.get("personLinks"), getPersonLinksKeys()));
  		additionInfo.put("skills", getMapOnListInfo(jsonObject.get("skills"), getSkillsKeys()));
        additionInfo.put("education", getMapOnListInfo(jsonObject.get("education"), getEducationKeys()));
        additionInfo.put("employmentHistory", JSONUtil.special(jsonObject.get("employmentHistory")));
        additionInfo.put("certificate", getMapOnListInfo(jsonObject.get("certificate"), getCertificateKeys()));

        PersonResumeService service = new PersonResumeService();
        Person person = service.newPerson(personInfo, additionInfo);
        
        
  		

	}*/
}
