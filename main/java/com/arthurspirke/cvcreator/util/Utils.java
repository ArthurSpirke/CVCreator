package com.arthurspirke.cvcreator.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.MainPersonalDAO;
import com.arthurspirke.cvcreator.dblayer.ProjectOnJobDAO;
import com.arthurspirke.cvcreator.entity.EncodedControl;
import com.arthurspirke.cvcreator.entity.Places;
import com.arthurspirke.cvcreator.entity.domain.Certificate;
import com.arthurspirke.cvcreator.entity.domain.Education;
import com.arthurspirke.cvcreator.entity.domain.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.domain.LinksToFiles;
import com.arthurspirke.cvcreator.entity.domain.PersonLinks;
import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.entity.domain.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.domain.ProjectOnJob;
import com.arthurspirke.cvcreator.entity.domain.Skills;
import com.arthurspirke.cvcreator.entity.enums.Language;

public class Utils {
	private static Logger log = Logger.getLogger(Utils.class);

	
	public static int getUniqueUserId(String firstName, String secondName) {
        byte[] firstArray = firstName.getBytes();
        int fCount = 0;
        
        for(byte b : firstArray){
        	fCount += b;
        }
        
        byte[] secondArray = secondName.getBytes();
        int sCount = 0;
        
        for(byte b : secondArray){
        	sCount += b;
        }
        
		long time = System.currentTimeMillis();
		long result = (time / 1000) - (100_000_000 - (sCount+fCount));

		return (int) result;
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

	
	
	public static List<String> getListOfPlacesNamesByChosenLang(Language chosenLang, List<Places> placesList){
		List<String> returnList = new ArrayList<>();
		
		if(chosenLang.equals(Language.RUSSIAN)){
			for(Places place : placesList){
				returnList.add(place.getRuName());
			}
		} else {
			for(Places place : placesList){
				returnList.add(place.getEnName());
			}
		}
		
		return returnList;
	}
	
	public static List<Integer> getListOfIdByPlaces(List<Places> listOfPlaces){
		List<Integer> returnList = new ArrayList<>();
		
		for(int i = 0; i < listOfPlaces.size(); i++){
			returnList.add(listOfPlaces.get(i).getId());
		}
		
		return returnList;
	}

	public static List<String> getEducationTypes(){
		String[] types = AppProperties.getEduTypes().split(",");
		return Arrays.asList(types);
	}
	
	public static List<String> getCertificateSizeList(){
		String[] sizes = AppProperties.getCertSizes().split(",");
		return Arrays.asList(sizes);
		
	}
	
	public static List<PhoneNumbers> getPhoneNumbersListByState(List<PhoneNumbers> phones, String state){
		List<PhoneNumbers> phonesList = new ArrayList<>();
		for(PhoneNumbers ph : phones){
			if(ph.getState().equals(state)){
				phonesList.add(ph);
			}
		}
		
		return phonesList;
	}
	
	public static List<PersonLinks> getPersonLinksListByState(List<PersonLinks> personLinks, String state){
		List<PersonLinks> personLinksList = new ArrayList<>();
		for(PersonLinks links : personLinks){
			if(links.getState().equals(state)){
				personLinksList.add(links);
			}
		}
		
		return personLinksList; 
	}
	
	public static List<Skills> getSkillsListByState(List<Skills> skills, String state){
		List<Skills> skillsList = new ArrayList<>();		
		for(Skills skill : skills){
			if(skill.getState().equals(state)){
				skillsList.add(skill);
			}
		}
		return skillsList;
	}
	
	public static List<Education> getEducationListByState(List<Education> education, String state){
		List<Education> eduList = new ArrayList<>();
		for(Education edu : education){
			if(edu.getState().equals(state)){
				eduList.add(edu);
			}
		}
		return eduList;
	}
	
	public static List<EmploymentHistory> getEmploymentHistoryListByState(List<EmploymentHistory> employment, String state){
		List<EmploymentHistory> empList = new ArrayList<>();
		for(EmploymentHistory emp : employment){
			if(emp.getState().equals(state)){
				empList.add(emp);
			}
		}
		return empList;
	}
	
	public static List<ProjectOnJob> getProjectsListByState(List<ProjectOnJob> projects, String state){
		List<ProjectOnJob> returnList = new ArrayList<>();
		for(ProjectOnJob proj : projects){
			if(proj.getState().equals(state)){
				returnList.add(proj);
			}
		}
		
		return returnList;
	}
	
	public static List<Certificate> getCertificateListByState(List<Certificate> certificate, String state){
		List<Certificate> certList = new ArrayList<>();
		for(Certificate cert : certificate){
			if(cert.getState().equals(state)){
				certList.add(cert);
			}
		}
		return certList;
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
	
	
	public static List<String> getPlacesNameByLang(List<Places> placesList, String lang){
		List<String> returnList = new ArrayList<>();
		
		if(lang.equals("Russian")){
			for(int i = 0; i < placesList.size(); i++){
				returnList.add(placesList.get(i).getRuName());
			}
		} else {
			for(int i = 0; i < placesList.size(); i++){
				returnList.add(placesList.get(i).getEnName());
			}
		}
		return returnList;
	}
	
	
	
	public static String getPlaceNameByLang(Places place, Language lang){
		if(lang.equals(Language.RUSSIAN)){
            return place.getRuName();
		} else {
            return place.getEnName();
		}
	}
	
	public static List<ProjectOnJob> listOfAllProjectsByAllEmploymentHistory(List<EmploymentHistory> employmentList){
		List<ProjectOnJob> returnList = new ArrayList<>();
		
		for(int i = 0; i < employmentList.size(); i++){
			for(int j = 0; j < employmentList.get(i).getProjects().size(); j++){
				returnList.add(employmentList.get(i).getProjects().get(j));
			}
		}
		return returnList;
	}
	
	public static void projectChangerInDB(List<ProjectOnJob> projectList, Integer personId, Integer companyId){
		  ProjectOnJobDAO dao = new ProjectOnJobDAO();
		  dao.updateProjectsOnJobById(getProjectsListByState(projectList, "upd"));
		  dao.addProjectOnJob(personId, companyId, getProjectsListByState(projectList, "new"));
		  dao.deleteProjectsOnJobById(getProjectsListByState(projectList, "del"));
	}
	
	public static LinksToFiles getLinksToFilesByPersonId(Integer personId){
		MainPersonalDAO dao = new MainPersonalDAO();
		PersonalInfo p = dao.getPersonById(personId);
        return p.getLinksToFiles();
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
				returnList.add(Integer.parseInt(checkList.get(i)));
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

}
