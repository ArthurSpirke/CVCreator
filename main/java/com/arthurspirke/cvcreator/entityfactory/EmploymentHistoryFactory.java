package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.domain.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.domain.ProjectOnJob;

import static com.arthurspirke.cvcreator.util.Utils.*;


public class EmploymentHistoryFactory {
	public static List<EmploymentHistory> getEmploymentHistoryList(int entityCount, int personId, List<Map<String, String>> employmentList, List<ProjectOnJob> projectList){
		List<EmploymentHistory> returnList = new ArrayList<>();
		if(entityCount != 0){
			for(Map<String, String> map : employmentList){
				returnList.add(newFullEmploymentHistory(personId, map, projectList));
			}
		}
		return returnList;
	}
	
	
	public static EmploymentHistory newFullEmploymentHistory(int personId, Map<String, String> map, List<ProjectOnJob> projectList){
		 return new EmploymentHistory(getInteger(map.get("empId")), personId, map.get("empTitle"), map.get("empYears"), map.get("empPosition"), getInteger(map.get("empCountry")), getInteger(map.get("empRegion")), getInteger(map.get("empCity")), map.get("empDescription"), ProjectOnJobFactory.getProjectsListByCompanyLink(map.get("companyLink"), projectList), map.get("empState"));
	}
}
