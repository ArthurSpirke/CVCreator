package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.util.Utils;

public class Project extends Component{
	
	private String companyId;
	private final String title;
	private final String position;
	private final String years;
	private final String description;
	private final String companyLink;

	public Project(String id, String companyId, String personId, String projTitle,
			String projPosition, String projYears, String projDescription){
		this(id, companyId, personId, projTitle, projPosition, projYears, projDescription, "", "");
	}
	
	public Project(String id, String companyId, String personId, String projTitle,
			String projPosition, String projYears, String projDescription,
			String companyLink, String state) {
		super(id, personId, state);
		this.companyId = companyId;
		this.title = projTitle;
		this.position = projPosition;
		this.years = projYears;
		this.description = projDescription;
		this.companyLink = companyLink;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getTitle() {
		return title;
	}

	public String getPosition() {
		return position;
	}

	public String getYears() {
		return years;
	}

	public String getDescription() {
		return description;
	}

	public String getCompanyLink() {
		return companyLink;
	}

	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Project project = (Project) o;
		
		boolean idCheck = id == project.id || (id != null && id.equals(project.getId()));
		boolean personIdCheck = personId == project.personId || (personId != null && personId.equals(project.getPersonId()));
		boolean companyIdCheck = companyId == project.companyId || (companyId != null && companyId.equals(project.getCompanyId()));
		boolean titleCheck = title == project.title || (title != null && title.equals(project.getTitle()));
		boolean positionCheck = position == project.position || (position != null && position.equals(project.getPosition()));
		boolean yearsCheck = years == project.years || (years != null && years.equals(project.getYears()));
		boolean descriptionCheck = description == project.description || (description != null && description.equals(project.getDescription()));
		
		return idCheck && personIdCheck && companyIdCheck && titleCheck && positionCheck && positionCheck && yearsCheck && descriptionCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (companyId == null ? 0 : companyId.hashCode());
		hash = hash * 31 + (title == null ? 0 : title.hashCode());
		hash = hash * 31 + (position == null ? 0 : position.hashCode());
		hash = hash * 31 + (years == null ? 0 : years.hashCode());
		hash = hash * 31 + (description == null ? 0 : description.hashCode());
		return hash;
	}
}
