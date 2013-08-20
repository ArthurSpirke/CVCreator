package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.domain.ProjectOnJob;

import static com.arthurspirke.cvcreator.util.Utils.*;

public class ProjectOnJobFactory {
	public static List<ProjectOnJob> getProjectOnJobList(int entityCount, int personId, List<Map<String, String>> projectOnJobList){
		List<ProjectOnJob> returnList = new ArrayList<>();
		if(entityCount != 0){
			for(Map<String, String> map : projectOnJobList) {
				returnList.add(newFullProject(personId, map));
			}
		}
		return returnList;
	}
	
	public static ProjectOnJob newFullProject(int personId, Map<String, String> d){
		return new ProjectOnJob(getInteger(d.get("projId")), getInteger(d.get("companyId")), personId, d.get("projTitle"), d.get("projPosition"), d.get("projYears"), d.get("projDescription"), d.get("companyLink"), d.get("projState"));
	}
	
	public static List<ProjectOnJob> getProjectsListByCompanyLink(String companyLink, List<ProjectOnJob> projectList){
		List<ProjectOnJob> projects = new ArrayList<>();
		for(ProjectOnJob project : projectList){
			if(companyLink.equals(project.getCompanyLink())){
				projects.add(project);
			}
		}
		
		return projects;
	}
	
	
}
