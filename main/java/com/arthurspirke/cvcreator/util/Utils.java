package com.arthurspirke.cvcreator.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Pattern;


import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.support.Places;
import com.arthurspirke.cvcreator.service.support.EncodedControl;

public class Utils {
	private static Logger log = Logger.getLogger(Utils.class);
	private static final String NEW_ENTITY = "0";

	
	public static String getUniqueId(){
		return UUID.randomUUID().toString();
	}
	
	public static String getRealId(String id){
		if(id.equals(NEW_ENTITY)){
			return getUniqueId();
		} else {
			return id;
		}
	}

	public static String[] getArrayOfFutureFiles(int personId) {
		String path = AppProperties.getPathToSaveFinalDocs();
		String[] returnArray = new String[3];

		returnArray[0] = path + personId + "/" + personId + ".pdf";
		returnArray[1] = path + personId + "/" + personId + ".doc";
		returnArray[2] = path + personId + "/" + personId + ".html";

		return returnArray;
	}



	public static String getCutPath(String fullPath) {
		String c = AppProperties.getCuttingPath();
		return fullPath.replace(c, "");
	}

	
	public static ResourceBundle getResourceBundleByLang(Language currentLang){
		Locale currentLocale = null;
            
		log.debug("CURRENT LANG - " + currentLang);
		if(currentLang.equals(Language.ENGLISH)){
			currentLocale = new Locale("en", "US");
		} else if (currentLang.equals(Language.RUSSIAN)){
			currentLocale = new Locale("ru", "RU");
		}

		return ResourceBundle.getBundle("com.arthurspirke.cvcreator.locale.transtest", currentLocale, new EncodedControl("UTF-8"));
    
	}


	public static List<Project> listOfAllProjectsByAllEmploymentHistory(List<EmploymentHistory> employmentList){
		List<Project> returnList = new ArrayList<>();
		
		for(int i = 0; i < employmentList.size(); i++){
			for(int j = 0; j < employmentList.get(i).getProjects().size(); j++){
				returnList.add(employmentList.get(i).getProjects().get(j));
			}
		}
		return returnList;
	}
	
	
	public static int getInteger(String dataFromFilter){
		if("".equals(dataFromFilter)) return 0;
		if(dataFromFilter == null) return 0;
		Pattern pattern = Pattern.compile(".*[^0-9].*");
		
		boolean isInteger = pattern.matcher((String)dataFromFilter).matches();
		
		if(isInteger) return 0;
		
		return Integer.parseInt((String) dataFromFilter);
	}
	
	
	public static List<Integer> getListOfIntegers(Object listOfDataFromFilter){
		List<Integer> returnList = new ArrayList<>();
		List<String> checkList = (List<String>) listOfDataFromFilter;
		for(int i = 0; i < checkList.size(); i++){
			if(checkList.get(i).equals("")){
				returnList.add(0);
			} else {
				returnList.add(getInteger(checkList.get(i)));
			}
		}
		
		return returnList;
	}
	
	public static String[] replaceDollarSignToSharp(String[] array){
		for(int i = 0; i < array.length; i++){
			if(array[i].startsWith("$")){
				array[i] = array[i].replace("$", "#");
			}
		}
		
		return array;
	}
	
	public static String replaceSharpToDollarSign(String key){
		if(key.startsWith("#")){
			return key.replace("#", "$");
		}
		return key;
	}
	
	public static List<Project> assemblyProjectsFromCompanies(List<EmploymentHistory> companies){
		List<Project> projects = new ArrayList<>();
		
		for(EmploymentHistory company : companies){
			projects.addAll(company.getProjects());
		}
		
		return projects;
	}

}
