package com.arthurspirke.cvcreator.pdf;

import static com.arthurspirke.cvcreator.util.AppProperties.getAddressKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getCertificateKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getEducationKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonLinksKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonTemplatesKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPersonalInfoKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getPhoneNumbersKeys;
import static com.arthurspirke.cvcreator.util.AppProperties.getSkillsKeys;
import static com.arthurspirke.cvcreator.util.JSONUtil.getMapInfo;
import static com.arthurspirke.cvcreator.util.JSONUtil.getMapOnListInfo;

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
import org.junit.BeforeClass;
import org.junit.Test;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.enums.OperationType;
import com.arthurspirke.cvcreator.service.PersonResumeService;
import com.arthurspirke.cvcreator.service.generators.ResumeGenerator;
import com.arthurspirke.cvcreator.util.JSONUtil;

public class CreateResumeTest {
    private static Person person = null;
	private final static File FILE_WITH_JSON = new File("C:/Users/Arthur/Downloads/testJson.txt");
    private static String json = "";
	
	//@BeforeClass
	public static void init(){
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
        person = service.newPerson(personInfo, additionInfo);
   
	}
	
	//@Test
	public void testGenerateResume(){
		ResumeGenerator generator = new ResumeGenerator(person, OperationType.GENERATE);
		generator.execute();
	}
}
