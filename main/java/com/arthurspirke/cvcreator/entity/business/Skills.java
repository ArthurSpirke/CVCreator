package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.util.Utils;


public class Skills extends Component{

	private final String skillsName;
	private final String skillsValue;

	
	public Skills(String id, String personId, String skillsName, String skillsValue, String state) {
		super(id, personId, state);
        this.skillsName = skillsName;
        this.skillsValue = skillsValue;
	}

	public String getSkillsName() {
		return skillsName;
	}

	public String getSkillsValue() {
		return skillsValue;
	}	
  
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Skills skills = (Skills) o;
		
		boolean idCheck = id == skills.id || (id != null && id.equals(skills.getId()));
		boolean personIdCheck = personId == skills.personId || (personId != null && personId.equals(skills.getPersonId()));
		boolean skillsNameCheck = skillsName == skills.skillsName || (skillsName != null && skills.equals(skills.getSkillsName()));
		boolean skillsValueCheck = skillsValue == skills.skillsValue || (skillsValue != null && skills.equals(skills.getSkillsValue()));
		
		return idCheck && personIdCheck && skillsNameCheck && skillsValueCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (skillsName == null ? 0 : skillsName.hashCode());
		hash = hash * 31 + (skillsValue == null ? 0 : skillsValue.hashCode());
		return hash;
	}

}
