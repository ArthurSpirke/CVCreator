package com.arthurspirke.cvcreator.pdf;

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
import static com.arthurspirke.cvcreator.service.JSONGenerator.*;
import static com.arthurspirke.cvcreator.service.JSONParse.*;
import com.arthurspirke.cvcreator.service.PersonResumeService;
import com.arthurspirke.cvcreator.service.generators.ResumeGenerator;

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
		
		
        JSONObject jsonObject = getJsonObject(json);
		
  		Map<String, List<Map<String, String>>> additionInfo = new HashMap<>();
  		Map<String, String> personInfo = getMapFromJson(jsonObject.get("person"));
  		
  		additionInfo.put("address", getListMapFromJson(jsonObject.get("address")));
  		additionInfo.put("personalInfo", getListMapFromJson(jsonObject.get("personalInfo")));
  		additionInfo.put("personalTemplates", getListMapFromJson(jsonObject.get("personalTemplates")));
  		additionInfo.put("phoneNumbers", getListMapFromJson(jsonObject.get("phoneNumbers")));
  		additionInfo.put("personLinks", getListMapFromJson(jsonObject.get("personLinks")));
  		additionInfo.put("skills", getListMapFromJson(jsonObject.get("skills")));
        additionInfo.put("education", getListMapFromJson(jsonObject.get("education")));
        additionInfo.put("employmentHistory", getListMapForEmpHistory(jsonObject.get("employmentHistory")));
        additionInfo.put("certificate", getListMapFromJson(jsonObject.get("certificate")));

        PersonResumeService service = new PersonResumeService();
        person = service.newPerson(personInfo, additionInfo);
   
	}
	
	//@Test
	public void testGenerateResume(){
		ResumeGenerator generator = new ResumeGenerator(person, OperationType.GENERATE);
		generator.execute();
	}
}
