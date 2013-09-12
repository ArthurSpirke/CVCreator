package com.arthurspirke.cvcreator.entity.business;


import java.util.List;



public class EmploymentHistory extends Component{

	private Address address;
	private final String title;
	private final String years;
	private final String position;
	private final String description;
	private List<Project> projects;
	
	
	public EmploymentHistory(String id, String personId, String empTitle, String empYears, String empPosition, String empDescription, Address address, String state){
		this(id, personId, empTitle, empYears, empPosition, empDescription, address, null, state);
	}

	
	public EmploymentHistory(String id, String personId, String empTitle, String empYears, String empPosition, String empDescription){
		this(id, personId, empTitle, empYears, empPosition, empDescription, null, null, "");
	}
	
	public EmploymentHistory(String id, String personId, String empTitle, String empYears, String empPosition, String empDescription, Address address, List<Project> projectList, String state) {
		super(id, personId, state);
		this.title = empTitle;
		this.years = empYears;
		this.position = empPosition;
		this.description = empDescription;
		this.address = address;
		this.projects = projectList;
		
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public String getTitle() {
		return title;
	}


	public String getYears() {
		return years;
	}


	public String getPosition() {
		return position;
	}


	public String getDescription() {
		return description;
	}

	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects){
		this.projects = projects;
	}
	
	@Override
    public boolean equals(Object o){
    	if(o == null || o.getClass() != this.getClass()) return false;
    	EmploymentHistory emp = (EmploymentHistory) o;
    	
		boolean idCheck = id == emp.id || (id != null && id.equals(emp.getId()));
		boolean personIdCheck = personId == emp.personId || (personId != null && personId.equals(emp.getPersonId()));
		boolean addressCheck = address == emp.address || (address != null && address.equals(emp.getAddress()));
		boolean titleCheck = title == emp.title || (title != null && title.equals(emp.getTitle()));
		boolean yearsCheck = years == emp.years || (years != null && years.equals(emp.getYears()));
		boolean positionCheck = position == emp.position || (position != null && position.equals(emp.getPosition()));
		boolean descriptionCheck = description == emp.description || (description != null && description.equals(emp.getDescription()));
		boolean projectsCheck = projects == emp.projects || (projects != null && projects.equals(emp.getProjects()));
		
		return idCheck && personIdCheck && addressCheck && titleCheck && yearsCheck && positionCheck && descriptionCheck && projectsCheck;
    }
    
	@Override
    public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (address == null ? 0 : address.hashCode());
		hash = hash * 31 + (title == null ? 0 : title.hashCode());
		hash = hash * 31 + (years == null ? 0 : years.hashCode());
		hash = hash * 31 + (position == null ? 0 : position.hashCode());
		hash = hash * 31 + (description == null ? 0 : description.hashCode());
		hash = hash * 31 + (projects == null ? 0 : projects.hashCode());
		return hash;
    }

}
