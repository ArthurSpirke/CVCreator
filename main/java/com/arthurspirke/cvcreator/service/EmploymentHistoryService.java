package com.arthurspirke.cvcreator.service;

import static com.arthurspirke.cvcreator.util.Utils.getInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.DAOFactory;
import com.arthurspirke.cvcreator.dblayer.MainDAO;
import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.JSONUtil;
import com.arthurspirke.cvcreator.util.Utils;

public class EmploymentHistoryService implements DBService<EmploymentHistory>, FactoryService<EmploymentHistory> {
	
	MainDAO<EmploymentHistory> employmentHistoryDAO = DAOFactory.getDAO(EntityType.EMPLOYMENT_HISTORY);
	ProjectService projectService = new ProjectService();

	@Override
	public void save(List<EmploymentHistory> employmentHistory) throws ComponentWriteException {
		employmentHistoryDAO.insert(employmentHistory);

		List<Project> projectList = new ArrayList<>();

		for (EmploymentHistory emp : employmentHistory) {
			projectList.addAll(emp.getProjects());
		}

		projectService.save(projectList);
	}

	@Override
	public EmploymentHistory getEntity(String id)
			throws ComponentAssemblyException {
		EmploymentHistory employmentHistory = employmentHistoryDAO.getById(id);
		List<Project> projectList = projectService.getEntitiesList(employmentHistory.getPersonId());

		for (Project proj : projectList) {
			if (employmentHistory.getId() == proj.getCompanyId()) {
				employmentHistory.getProjects().add(proj);
			}
		}

		return employmentHistory;
	}

	@Override
	public List<EmploymentHistory> getEntitiesList(String personId)
			throws ComponentAssemblyException {
		List<EmploymentHistory> empHistoryList = employmentHistoryDAO
				.getListByPersonId(personId);
		List<Project> projectList = projectService.getEntitiesList(personId);

		for (EmploymentHistory emp : empHistoryList) {
			for (Project proj : projectList) {
				if (emp.getId() == proj.getCompanyId()) {
					emp.getProjects().add(proj);
				}
			}
		}

		return empHistoryList;
	}

	@Override
	public void save(EmploymentHistory entity) throws ComponentWriteException {
		employmentHistoryDAO.insert(entity);
		projectService.save(entity.getProjects());
	}

	@Override
	public List<EmploymentHistory> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		List<EmploymentHistory> returnList = new ArrayList<>();
		if(entitiesCount != 0){
			for(Map<String, String> map : list){
				returnList.add(getEntity(personId, map));
			}
		}
		return returnList;
	}

	@Override
	public EmploymentHistory getEntity(String personId, Map<String, String> map) {
		
		Map<String, String> empHistoryMainInfo = JSONParse.getMapFromJsonString(map.get("empMainInfo"));
		Map<String, String> empHistoryAddressInfo = JSONParse.getMapFromJsonString(map.get("empAddress"));
		List<Map<String, String>> empProjectsInfo = JSONParse.getListMapFromJsonString(map.get("projects"));
				
        return getEntity(personId, empHistoryMainInfo, empHistoryAddressInfo, empProjectsInfo);
	}
	
	private EmploymentHistory getEntity(String personId, Map<String, String> empHistoryMainInfo, Map<String, String> empHistoryAddressInfo, List<Map<String, String>> empProjectsInfo){
		
		String empHisId = Utils.getRealId(empHistoryMainInfo.get("empId"));
		int projectsCount = Utils.getInteger(empHistoryMainInfo.get("projectCount"));
		
		Address address = empHistoryAddress(empHisId, personId, empHistoryAddressInfo);
		List<Project> projects = getProjectsForCompany(projectsCount, empHisId, personId, empProjectsInfo);
		
		return getEmploymentHistory(empHisId, personId, address, projects, empHistoryMainInfo);	
	}

	private Address empHistoryAddress(String hostId, String personId, Map<String, String> empHistoryAddressInfo){
		
		String id = Utils.getRealId(empHistoryAddressInfo.get("id"));
		int countryId = getInteger(empHistoryAddressInfo.get("countryId"));
		int regionId = getInteger(empHistoryAddressInfo.get("regionId"));
		int cityId = getInteger(empHistoryAddressInfo.get("cityId"));
		Language lang = Language.getLanguage(empHistoryAddressInfo.get("prefLang"));
		String state = empHistoryAddressInfo.get("state");
		
		return new Address(id, hostId, personId, countryId, regionId, cityId, lang, state);
	}
	
	private List<Project> getProjectsForCompany(int projectsCount, String companyId, String personId, List<Map<String, String>> projectInfo){
		 List<Map<String, String>> modifyProjectsWithCompanyId = JSONUtil.modifyProjectMapInfo(projectInfo, companyId);
		 
		 List<Project> projects = projectService.getEntitiesList(projectsCount, personId, modifyProjectsWithCompanyId);
		 
		 return projects;
	}
	
	private EmploymentHistory getEmploymentHistory(String empHisId, String personId, Address address, List<Project> projects, Map<String, String> empHistoryMainInfo){
		
		String title = empHistoryMainInfo.get("empTitle");
		String years = empHistoryMainInfo.get("empYears");
		String position = empHistoryMainInfo.get("empPosition");
		String description = empHistoryMainInfo.get("empDescription");
		String state = empHistoryMainInfo.get("empState");
		
		return new EmploymentHistory(empHisId, personId, title, years, position, description, address, projects, state);
	}
	
	@Override
	public void update(EmploymentHistory entity) throws ComponentWriteException {
         employmentHistoryDAO.update(entity);
	}

	@Override
	public void update(List<EmploymentHistory> entities) throws ComponentWriteException {
		employmentHistoryDAO.update(entities);
	}

	@Override
	public void delete(EmploymentHistory entity) throws ComponentWriteException {
		String id = entity.getId();
		employmentHistoryDAO.delete(id);
	}

	@Override
	public void delete(List<EmploymentHistory> entities) throws ComponentWriteException {
	       String[] ids = new String[entities.size()];
	       int length = ids.length;
	       
	       for(int i = 0; i < length; i++){
	    	   ids[i] = entities.get(i).getId();
	       }
	       
	       employmentHistoryDAO.delete(ids);
		
	}
}
