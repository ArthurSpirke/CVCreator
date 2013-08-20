package com.arthurspirke.cvcreator.entity.domain;


public class Skills {

	private final int id;
	private final int personId;
	private final String skillsName;
	private final String skillsValue;
	private final String state;
	
	
	public Skills(int personId, String skillsName, String skillsValue) {
           this(0, personId, skillsName, skillsValue);
	}
	
	public Skills(int id, int personId, String skillsName, String skillsValue) {
          this(id, personId, skillsName, skillsValue, "");
	}
	
	public Skills(int id, int personId, String skillsName, String skillsValue, String state) {
		this.id = id;
		this.personId = personId;
        this.skillsName = skillsName;
        this.skillsValue = skillsValue;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

	public String getSkillsName() {
		return skillsName;
	}

	public String getSkillsValue() {
		return skillsValue;
	}

	public String getState() {
		return state;
	}
	
  

}
