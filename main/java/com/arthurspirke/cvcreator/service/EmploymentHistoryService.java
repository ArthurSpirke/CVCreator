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
	AddressService addressService = new AddressService();

	@Override
	public void save(List<EmploymentHistory> employmentHistory) throws ComponentWriteException {
		
		List<Project> projectList = projectService.getAllProjectsFromCompanies(employmentHistory);
		List<Address> addressList = addressService.getAddressesFromEmploymentHistory(employmentHistory);
		
		employmentHistoryDAO.insert(employmentHistory);
		projectService.save(projectList);
		addressService.save(addressList);
	}


	@Override
	public EmploymentHistory getEntity(String id) throws ComponentAssemblyException {
		EmploymentHistory employmentHistory = employmentHistoryDAO.getById(id);
		String hostId = employmentHistory.getId();
		
		List<Project> projects = projectService.getProjectsByHostCompany(hostId);
        Address address = addressService.getAddressByHost(hostId);
		
		employmentHistory.setProjects(projects);
		employmentHistory.setAddress(address);

		return employmentHistory;
	}

	@Override
	public List<EmploymentHistory> getEntitiesList(String personId) throws ComponentAssemblyException {
		List<EmploymentHistory> empHistoryList = employmentHistoryDAO.getListByPersonId(personId);

		for(EmploymentHistory empHistory : empHistoryList){
			String hostId = empHistory.getId();
			
			List<Project> projects = projectService.getProjectsByHostCompany(hostId);
			Address address = addressService.getAddressByHost(hostId);
			
			empHistory.setAddress(address);
			empHistory.setProjects(projects);
		}
		
		return empHistoryList;
	}

	@Override
	public void save(EmploymentHistory entity) throws ComponentWriteException {
		employmentHistoryDAO.insert(entity);
		projectService.save(entity.getProjects());
		addressService.save(entity.getAddress());
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
         addressService.update(entity.getAddress());
         projectService.update(entity.getProjects());
	}

	@Override
	public void update(List<EmploymentHistory> entities) throws ComponentWriteException {
		
		List<Address> addressList = addressService.getAddressesFromEmploymentHistory(entities);
		List<Project> projects = projectService.getAllProjectsFromCompanies(entities);
		
		addressService.update(addressList);
		projectService.update(projects);
		
		employmentHistoryDAO.update(entities);
	}

	@Override
	public void delete(EmploymentHistory entity) throws ComponentWriteException {
		String id = entity.getId();
		
		Address address = entity.getAddress();
		List<Project> projects = entity.getProjects();
		
		addressService.delete(address);
		projectService.delete(projects);
		
		employmentHistoryDAO.delete(id);
		
	}

	@Override
	public void delete(List<EmploymentHistory> entities) throws ComponentWriteException {
           
		   List<Address> addresses = addressService.getAddressesFromEmploymentHistory(entities);
		   List<Project> projects = projectService.getAllProjectsFromCompanies(entities);
		
		   addressService.delete(addresses);
		   projectService.delete(projects);
	       employmentHistoryDAO.delete(Utils.getIdsByComponents(entities));
		
	}
}
