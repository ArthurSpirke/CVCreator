package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.DAOFactory;
import com.arthurspirke.cvcreator.dblayer.MainDAO;
import com.arthurspirke.cvcreator.dblayer.ProjectDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcProjectDAO;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class ProjectService implements DBService<Project>, FactoryService<Project> {
	
	JdbcProjectDAO projectDAO = new JdbcProjectDAO();

	@Override
	public void save(List<Project> projects) throws ComponentWriteException {
		projectDAO.insert(projects);
	}

	@Override
	public Project getEntity(String id) throws ComponentAssemblyException {
		return projectDAO.getById(id);
	}

	@Override
	public List<Project> getEntitiesList(String personId) throws ComponentAssemblyException {
		return projectDAO.getListByPersonId(personId);
	}

	@Override
	public void save(Project entity) throws ComponentWriteException {
		projectDAO.insert(entity);
	}

	@Override
	public List<Project> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		
		List<Project> returnList = new ArrayList<>();
		
		if(entitiesCount != 0){
			for(Map<String, String> map : list) {
				returnList.add(getEntity(personId, map));
			}
		}
		
		return returnList;
	}

	@Override
	public Project getEntity(String personId, Map<String, String> map) {	
		
		String id = Utils.getRealId(map.get("projId"));
		String companyId = map.get("companyId");
		String title = map.get("projTitle");
		String position = map.get("projPosition");
		String years = map.get("projYears");
		String description = map.get("projDescription");
		//TODO: need this?
		String companyLink = map.get("companyLink");
		String state = map.get("projState");
		
        return new Project(id, companyId, personId, title, position, years, description, companyLink, state);
	
	}

	@Override
	public void update(Project entity) throws ComponentWriteException {
        projectDAO.update(entity);
	}

	@Override
	public void update(List<Project> entities) throws ComponentWriteException {
        projectDAO.update(entities);
	}

	@Override
	public void delete(Project entity) throws ComponentWriteException {
        String id = entity.getId();
        projectDAO.delete(id);
	}

	@Override
	public void delete(List<Project> entities) throws ComponentWriteException {
	     String[] ids = Utils.getIdsByComponents(entities);
	     projectDAO.delete(ids);
	}
	
	
	public List<Project> getProjectsByHostCompany(String hostCompanyId) throws ComponentAssemblyException {
		return projectDAO.getListByHostId(hostCompanyId);
	}
	
	public List<Project> getAllProjectsFromCompanies(List<EmploymentHistory> empHistoryList){
		List<Project> projects = new ArrayList<>();
		
		for(EmploymentHistory empHistory : empHistoryList){
			projects.addAll(empHistory.getProjects());
		}
		
		return projects;
	}
	
}
