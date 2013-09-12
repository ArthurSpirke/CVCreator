package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.entity.enums.EducationType;


public class Education extends Component{

	private final String title;
	private final String years;
	private final String degree;
	private final String description;
    private Address address;
	private final EducationType type;
	
	
	public Education(String id, String personId, EducationType eduType, String eduTitle,
			String eduYears, String eduDegree, String eduDescription){
		this(id, personId, eduType, eduTitle, eduYears, eduDegree, null, eduDescription, "");
	}


	public Education(String id, String personId, EducationType eduType, String eduTitle,
			String eduYears, String eduDegree, Address address, String eduDescription, String state) {
		super(id, personId, state);
		this.type = eduType;
		this.title = eduTitle;
		this.years = eduYears;
		this.degree = eduDegree;
        this.address = address;
		this.description = eduDescription;
	}

	public EducationType getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getYears() {
		return years;
	}

	public String getDegree() {
		return degree;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address){
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Education edu = (Education) o;
		
		boolean idCheck = id == edu.id || (id != null && id.equals(edu.getId()));
		boolean personIdCheck = personId == edu.personId || (personId != null && personId.equals(edu.getPersonId()));
		boolean titleCheck = title == edu.title || (title != null && title.equals(edu.getTitle()));
		boolean yearsCheck = years == edu.years || (years != null && years.equals(edu.getYears()));
		boolean degreeCheck = degree == edu.degree || (degree != null && degree.equals(edu.getDegree()));
		boolean descriptionCheck = description == edu.description || (description != null && description.equals(edu.getDescription()));
		boolean addressCheck = address == edu.address || (address != null && address.equals(edu.getAddress()));
		boolean typeCheck = type == edu.type || (type != null && type.equals(edu.getType()));
		
		return idCheck && personIdCheck && titleCheck && yearsCheck && degreeCheck && descriptionCheck && addressCheck && typeCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (title == null ? 0 : title.hashCode());
		hash = hash * 31 + (years == null ? 0 : years.hashCode());
		hash = hash * 31 + (degree == null ? 0 : degree.hashCode());
		hash = hash * 31 + (description == null ? 0 : description.hashCode());
		hash = hash * 31 + (address == null ? 0 : address.hashCode());
		hash = hash * 31 + (type == null ? 0 : type.hashCode());
		return hash;
	}

	
}