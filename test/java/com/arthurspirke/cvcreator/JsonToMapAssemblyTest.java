package com.arthurspirke.cvcreator;

import static com.arthurspirke.cvcreator.util.AppProperties.getAddressKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getCertificateKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getEducationKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonLinksKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonTemplatesKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonalInfoKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPhoneNumbersKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getSkillsKeys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.arthurspirke.cvcreator.util.AppProperties;

public class JsonToMapAssemblyTest {

	/*
	private final static File FILE_WITH_JSON = new File("C:/Users/Arthur/Downloads/testJson.txt");
    JSONObject jsonObject;
	
	@Before
	public void loadJson(){
		try {
			InputStream in = new FileInputStream(FILE_WITH_JSON);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			StringBuilder sb = new StringBuilder();
			
			String line = "";
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			String json = sb.toString();
			jsonObject = JSONUtil.getJSONObject(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 *   "id": "0",
     *   "prefLang": "English",
     *   "personLinksCount": "2",
     *   "phoneNumbersCount": "2",
     *   "skillsCount": "2",
     *   "eduCount": "2",
     *   "empCount": "2",
     *   "certCount": "0"
	 
	@Test
	public void testPesonJsonToMapAssembly(){
		Map<String, String> personInfo = getMapInfo(jsonObject.get("person"), getPersonKeys());
		
		assertThat(personInfo.get("id"), is(equalTo("0")));
		assertThat(personInfo.get("prefLang"), is(equalTo("English")));
		assertThat(personInfo.get("personLinksCount"), is(equalTo("2")));
		assertThat(personInfo.get("phoneNumbersCount"), is(equalTo("2")));
		assertThat(personInfo.get("skillsCount"), is(equalTo("2")));
		assertThat(personInfo.get("eduCount"), is(equalTo("2")));
		assertThat(personInfo.get("empCount"), is(equalTo("2")));
		assertThat(personInfo.get("certCount"), is(equalTo("0")));		
	}
	
	
	 *   "id": "0",
     *   "countryId": "23",
     *   "regionId": "53",
     *   "cityId": "11",
     *   "streetAddress": "Main Street",
     *   "prefLang": "English"
	 
	@Test
	public void testAddressJsonMapAssembly(){
		List<Map<String, String>> address = getMapOnListInfo(jsonObject.get("address"), getAddressKeys());
		
		Map<String, String> first = address.get(0);
		
		assertThat(first.get("id"), is(equalTo("0")));
		assertThat(first.get("prefLang"), is(equalTo("English")));
		assertThat(first.get("countryId"), is(equalTo("23")));
		assertThat(first.get("regionId"), is(equalTo("53")));
		assertThat(first.get("cityId"), is(equalTo("11")));
		assertThat(first.get("postalCode"), is(equalTo("152362")));
		assertThat(first.get("streetAddress"), is(equalTo("Main Street")));

	}
	
	
	
	 *   "id": "0",
     *    "firstName": "John",
     *    "lastName": "Smith",
     *    "postalCode": "152362",
     *    "eMail": "john.smith1111111abc@gmail.com",
     *    "claimPosition": "Java Programmer",
     *    "profile": "My Profile",
     *    "hobbies": "My Hobbies",
     *    "state": "new"
	 
	@Test
	public void testPersonalInfoJsonMapAssembly(){
		List<Map<String, String>> personalInfo = getMapOnListInfo(jsonObject.get("personalInfo"), getPersonalInfoKeys());
		
		Map<String, String> first = personalInfo.get(0);
		
		assertThat(first.get("id"), is(equalTo("0")));
		assertThat(first.get("firstName"), is(equalTo("John")));
		assertThat(first.get("lastName"), is(equalTo("Smith")));
		assertThat(first.get("eMail"), is(equalTo("john.smith1111111abc@gmail.com")));
		assertThat(first.get("claimPosition"), is(equalTo("Java Programmer")));
		assertThat(first.get("profile"), is(equalTo("My Profile")));
		assertThat(first.get("hobbies"), is(equalTo("My Hobbies")));
		//need in PersonalInfo?
		assertThat(first.get("state"), is(equalTo("new")));
		
	}
	
	
	 *   "id": "0",
     *   "templatePDF": "SIMPLE",
     *   "templateHTML": "SIMPLE",
     *   "templateDOC": "SIMPLE",
     *   "state": "new"
	 
	@Test
	public void testPersonalTemplatesJsonMapAssembly(){
		List<Map<String, String>> personalTemplates = getMapOnListInfo(jsonObject.get("personalTemplates"), getPersonTemplatesKeys());
		
		Map<String, String> first = personalTemplates.get(0);
		
		assertThat(first.get("id"), is(equalTo("0")));
		assertThat(first.get("templatePDF"), is(equalTo("SIMPLE_PDF")));
		assertThat(first.get("templateHTML"), is(equalTo("SIMPLE_HTML")));
		assertThat(first.get("templateDOC"), is(equalTo("SIMPLE_DOC")));
		//need in PersonalInfo?
		assertThat(first.get("state"), is(equalTo("new")));
	}
	
	
	
	 *  "phId": "0",
     *  "phoneType": "mobilePhone",
     *  "number": "+14974352623",
     *  "phState": "new"
	 
	@Test
	public void testPhoneNumbersJsonMapAssembly(){
		List<Map<String, String>> phoneNumbers = getMapOnListInfo(jsonObject.get("phoneNumbers"), getPhoneNumbersKeys());
		
		Map<String, String> first = phoneNumbers.get(0);
		
		assertThat(first.get("phId"), is(equalTo("0")));
		assertThat(first.get("phoneType"), is(equalTo("Home")));
		assertThat(first.get("number"), is(equalTo("+14974352623")));
		assertThat(first.get("phState"), is(equalTo("new")));
	}
	
	
	
	 *  "linkId": "0",
     *  "linkType": "myLink",
     *  "link": "http://rerher.ru",
     *  "linkState": "new"
	 
	@Test
	public void testPersonLinksJsonMapAssembly(){
		List<Map<String, String>> personLinks = getMapOnListInfo(jsonObject.get("personLinks"), getPersonLinksKeys());
		
		Map<String, String> first = personLinks.get(0);
		
		assertThat(first.get("linkId"), is(equalTo("0")));
		assertThat(first.get("linkType"), is(equalTo("My link")));
		assertThat(first.get("link"), is(equalTo("http://rerher.ru")));
		assertThat(first.get("linkState"), is(equalTo("new")));
	}
	
	
	 *  "skillsId": "0",
     *  "skillsName": "Languages",
     *  "skillsValue": "Java, JavaScript, Scala",
     *  "skillsState": "new"
	 
	@Test
	public void testSkillsJsonMapAssembly(){
		List<Map<String, String>> skills = getMapOnListInfo(jsonObject.get("skills"), getSkillsKeys());
		
		Map<String, String> first = skills.get(0);
		
		assertThat(first.get("skillsId"), is(equalTo("0")));
		assertThat(first.get("skillsName"), is(equalTo("Languages")));
		assertThat(first.get("skillsValue"), is(equalTo("Java, JavaScript, Scala")));
		assertThat(first.get("skillsState"), is(equalTo("new")));
	}
	
	
	 *  "eduId": "0",
     *  "eduType": "UNIVERSITY",
     *  "eduTitle": "MIT",
     *  "eduYears": "2007-2013",
     *  "eduDegree": "Master of CS",
     *  "eduDescription": "Love this!",
     *  "prefLang": "English",
     *  "countryId": "216",
     *  "regionId": "444",
     *  "cityId": "523"
	 
	@Test
	public void testEducationJsonMapAssembly(){
		List<Map<String, String>> education = getMapOnListInfo(jsonObject.get("education"), getEducationKeys());
		
		Map<String, String> first = education.get(0);
		
		assertThat(first.get("eduId"), is(equalTo("0")));
		assertThat(first.get("eduType"), is(equalTo("University")));
		assertThat(first.get("eduTitle"), is(equalTo("MIT")));
		assertThat(first.get("eduDegree"), is(equalTo("Master of CS")));
		assertThat(first.get("eduDescription"), is(equalTo("Love this!")));
		assertThat(first.get("prefLang"), is(equalTo("English")));
		assertThat(first.get("countryId"), is(equalTo("216")));
		assertThat(first.get("regionId"), is(equalTo("444")));
		assertThat(first.get("cityId"), is(equalTo("523")));

	}
	
	
	 * ---First info bucket---
	 * 
	 * "mainEmpInfo":
	 * 
     * "empId": "0",
     * "empTitle": "Google",
     * "empYears": "2008-2013",
     * "empPosition": "Java Programmer",
     * "empDescription": "Love this!",
     * "empState": "new",
     * "projectCount": "1"
     *    
     * ---Second info bucket---
     *    
     * "empAddress":
     * 
     * "countryId": "121",
     * "regionId": "11",
     * "cityId": "423",
     * "prefLang": "English"
     *      
     *      
     * ---Third info bucket (Array)---
     *      
     * "projects":
     *       
     * "projId": "0",
     * "companyId": "0",
     * "projTitle": "MyProject",
     * "projPosition": "Java programmer",
     * "projYears": "2008-2013",
     * "projDescription": "Love this!",
     * "projState": "new"
     *
	 
	@Test
	public void testEmploymentHistoryJsonMapAssembly(){
		List<Map<String, String>> employmentHistory = JSONUtil.special(jsonObject.get("employmentHistory"));
		
		Map<String, String> first = employmentHistory.get(0);
		
		JSONObject mainInfoJson = JSONUtil.getJSONObject(first.get("empMainInfo"));
		JSONObject empAddressJson = JSONUtil.getJSONObject(first.get("empAddress"));
		JSONArray projectsJson = JSONUtil.getJSONArray(first.get("projects"));
		
		Map<String, String> mainInfo = getMapInfo(mainInfoJson, AppProperties.tempEmpMainInfoKeys());
	    Map<String, String> empAddress = getMapInfo(empAddressJson, AppProperties.tempEmpAddressKeys());
	    List<Map<String, String>> projects = getMapOnListInfo(projectsJson, AppProperties.tempProjectsKeys());
	    Map<String, String> project = projects.get(0);
	    
	    
	    //Main Info
		assertThat(mainInfo.get("empId"), is(equalTo("0")));
		assertThat(mainInfo.get("empTitle"), is(equalTo("Google")));
		assertThat(mainInfo.get("empYears"), is(equalTo("2008-2013")));
		assertThat(mainInfo.get("empPosition"), is(equalTo("Java Programmer")));
		assertThat(mainInfo.get("empDescription"), is(equalTo("Love this!")));
		assertThat(mainInfo.get("empState"), is(equalTo("new")));
		assertThat(mainInfo.get("projectCount"), is(equalTo("1")));

        //Address Info
		assertThat(empAddress.get("countryId"), is(equalTo("121")));
		assertThat(empAddress.get("regionId"), is(equalTo("11")));
		assertThat(empAddress.get("cityId"), is(equalTo("423")));
		assertThat(empAddress.get("prefLang"), is(equalTo("English")));
		
		//First project
		assertThat(project.get("projId"), is(equalTo("0")));
		assertThat(project.get("companyId"), is(equalTo("0")));
		assertThat(project.get("projTitle"), is(equalTo("MyProject")));
		assertThat(project.get("projPosition"), is(equalTo("Java programmer")));
		assertThat(project.get("projYears"), is(equalTo("2008-2013")));
		assertThat(project.get("projDescription"), is(equalTo("Love this!")));
		assertThat(project.get("projState"), is(equalTo("new")));

		
	}
	
	
	
	 * "certId": "0",
     * "certSize": "200x300",
     * "pathToImages": "http://gwelgjwegw.com/image.jpg",
     * "certSmallDesc": "Small Desc",
     * "certFullDesc": "Full Desc",
     * "certState": "new"
	 
	@Test
	public void testCertificateJsonMapAssembly(){
		List<Map<String, String>> certificate = getMapOnListInfo(jsonObject.get("certificate"), getCertificateKeys());
		
		Map<String, String> first = certificate.get(0);
		
		assertThat(first.get("certId"), is(equalTo("0")));
		assertThat(first.get("certSize"), is(equalTo("200x300")));
		assertThat(first.get("pathToImages"), is(equalTo("http://gwelgjwegw.com/image.jpg")));
		assertThat(first.get("certSmallDesc"), is(equalTo("Small Desc")));
		assertThat(first.get("certFullDesc"), is(equalTo("Full Desc")));
		assertThat(first.get("certState"), is(equalTo("new")));
	}*/
}
