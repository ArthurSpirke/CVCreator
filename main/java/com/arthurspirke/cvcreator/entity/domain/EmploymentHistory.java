package com.arthurspirke.cvcreator.entity.domain;

import java.util.List;


public class EmploymentHistory{
	private Integer id;
	private final int personId;
	private final String empTitle;
	private final int empCountry;
	private final int empRegion;
	private final int empCity;
	private final String empYears;
	private final String empPosition;
	private final String empDescription;
	private final String state;
	private final List<ProjectOnJob> projects;
	
	

	public EmploymentHistory(int personId, String empTitle, String empYears, String empPosition, int empCountry, int empRegion, int empCity, String empDescription, List<ProjectOnJob> projects){
		this(0, personId, empTitle, empYears, empPosition, empCountry, empRegion, empCity, empDescription, projects);
	}

	
	public EmploymentHistory(int id, int personId, String empTitle, String empYears, String empPosition, int empCountry, int empRegion, int empCity, String empDescription, List<ProjectOnJob> projects){
		this(id, personId, empTitle, empYears, empPosition, empCountry, empRegion, empCity, empDescription, projects, "");
	}
	
	public EmploymentHistory(int id, int personId, String empTitle, String empYears, String empPosition, int empCountry, int empRegion, int empCity, String empDescription, List<ProjectOnJob> projects, String state) {
		this.id = id;
		this.personId = personId;
		this.empTitle = empTitle;
		this.empCountry = empCountry;
		this.empRegion = empRegion;
		this.empCity = empCity;
		this.empYears = empYears;
		this.empPosition = empPosition;
		this.empDescription = empDescription;
		this.projects = projects;
		this.state = state;
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getPersonId() {
		return personId;
	}


	public String getEmpTitle() {
		return empTitle;
	}


	public int getEmpCountry() {
		return empCountry;
	}


	public int getEmpRegion() {
		return empRegion;
	}


	public int getEmpCity() {
		return empCity;
	}


	public String getEmpYears() {
		return empYears;
	}


	public String getEmpPosition() {
		return empPosition;
	}


	public String getEmpDescription() {
		return empDescription;
	}


	public String getState() {
		return state;
	}


	public List<ProjectOnJob> getProjects() {
		return projects;
	}

	

}
