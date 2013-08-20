package com.arthurspirke.cvcreator.entity.domain;

import com.arthurspirke.cvcreator.entity.enums.EducationType;


public class Education{
	private final int id;
	private final int personId;
	private final EducationType eduType;
	private final String eduTitle;
	private final String eduYears;
	private final String eduDegree;
	private final int eduCountry;
	private final int eduRegion;
	private final int eduCity;
	private final String eduDescription;
	private final String state;
	
	
	
	public Education(int personId, EducationType eduType, String eduTitle,
			String eduYears, String eduDegree, int eduCountry,
			int eduRegion, int eduCity, String eduDescription){
		this(0, personId, eduType, eduTitle, eduYears, eduDegree, eduCountry, eduRegion, eduCity, eduDescription);
	}
	
	public Education(int id, int personId, EducationType eduType, String eduTitle,
			String eduYears, String eduDegree, int eduCountry,
			int eduRegion, int eduCity, String eduDescription){
		this(id, personId, eduType, eduTitle, eduYears, eduDegree, eduCountry, eduRegion, eduCity, eduDescription, "");
	}


	

	public Education(int id, int personId, EducationType eduType, String eduTitle,
			String eduYears, String eduDegree, int eduCountry,
			int eduRegion, int eduCity, String eduDescription, String state) {
		super();
		this.id = id;
		this.personId = personId;
		this.eduType = eduType;
		this.eduTitle = eduTitle;
		this.eduYears = eduYears;
		this.eduDegree = eduDegree;
		this.eduCountry = eduCountry;
		this.eduRegion = eduRegion;
		this.eduCity = eduCity;
		this.eduDescription = eduDescription;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

	public EducationType getEduType() {
		return eduType;
	}

	public String getEduTitle() {
		return eduTitle;
	}

	public String getEduYears() {
		return eduYears;
	}

	public String getEduDegree() {
		return eduDegree;
	}

	public int getEduCountry() {
		return eduCountry;
	}

	public int getEduRegion() {
		return eduRegion;
	}

	public int getEduCity() {
		return eduCity;
	}

	public String getEduDescription() {
		return eduDescription;
	}

	public String getState() {
		return state;
	}

	
}