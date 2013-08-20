package com.arthurspirke.cvcreator.entity.domain;

public class ProjectOnJob {
	private final int id;
	private int companyId;
	private final int personId;
	private final String projTitle;
	private final String projPosition;
	private final String projYears;
	private final String projDescription;
	private final String companyLink;
	private final String state;

	public ProjectOnJob(int id, int companyId, int personId, String projTitle,
			String projPosition, String projYears, String projDescription){
		this(id, companyId, personId, projTitle, projPosition, projYears, projDescription, "", "");
	}
	
	public ProjectOnJob(int id, int companyId, int personId, String projTitle,
			String projPosition, String projYears, String projDescription,
			String companyLink, String state) {
		this.id = id;
		this.personId = personId;
		this.companyId = companyId;
		this.projTitle = projTitle;
		this.projPosition = projPosition;
		this.projYears = projYears;
		this.projDescription = projDescription;
		this.companyLink = companyLink;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public int getPersonId() {
		return personId;
	}

	public String getProjTitle() {
		return projTitle;
	}

	public String getProjPosition() {
		return projPosition;
	}

	public String getProjYears() {
		return projYears;
	}

	public String getProjDescription() {
		return projDescription;
	}

	public String getCompanyLink() {
		return companyLink;
	}

	public String getState() {
		return state;
	}

}
