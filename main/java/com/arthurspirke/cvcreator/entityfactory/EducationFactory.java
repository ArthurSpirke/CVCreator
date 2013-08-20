package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.arthurspirke.cvcreator.entity.domain.Education;
import com.arthurspirke.cvcreator.entity.enums.EducationType;

import static com.arthurspirke.cvcreator.util.Utils.*;

public class EducationFactory {
	public static List<Education> getEducationList(int entityCount, int personId, List<Map<String, String>> educationList){
		List<Education> returnList = new ArrayList<>();
		if(entityCount != 0){
			for(Map<String, String> map : educationList){
				returnList.add(newFullEducation(personId, map));
			}	
		}
		return returnList;
	}
	
	public static Education newFullEducation(int personId, Map<String, String> d){
		return new Education(getInteger(d.get("eduId")), personId, EducationType.getType(d.get("eduType")), d.get("eduTitle"), d.get("eduYears"), d.get("eduDegree"), getInteger(d.get("eduCountry")), getInteger(d.get("eduRegion")), getInteger(d.get("eduCity")), d.get("eduDescription"));
	}
	
}
