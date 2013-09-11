package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.util.Utils;

public class PersonalInfo extends Component{

	private final String firstName;
	private final String secondName;
	private final String claimPosition;
	private final String eMail;
	private final String profile;
	private final String hobbies;

	
	public PersonalInfo(String id, String personId, String firstName, String secondName, String claimPosition, String eMail, String profile, String hobbies){
		this(id, personId, firstName, secondName, claimPosition, eMail, profile, hobbies, "");
	}
	
	public PersonalInfo(String id, String personId, String firstName, String secondName, String claimPosition, String eMail, String profile, String hobbies, String state){
		super(id, personId, state);
		this.firstName = firstName;
		this.secondName = secondName;
		this.claimPosition = claimPosition;
		this.eMail = eMail;
		this.profile = profile;
		this.hobbies = hobbies;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getClaimPosition() {
		return claimPosition;
	}

	public String geteMail() {
		return eMail;
	}

	public String getProfile() {
		return profile;
	}

	public String getHobbies() {
		return hobbies;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		PersonalInfo personalInfo = (PersonalInfo) o;
		
		boolean firstNameCheck = firstName == personalInfo.firstName || (firstName != null && firstName.equals(personalInfo.getFirstName()));
		boolean secondNameCheck = secondName == personalInfo.secondName || (secondName != null && secondName.equals(personalInfo.getSecondName()));
		boolean claimPositionCheck = claimPosition == personalInfo.claimPosition || (claimPosition != null && claimPosition.equals(personalInfo.getClaimPosition()));
		boolean eMailCheck = eMail == personalInfo.eMail || (eMail != null && eMail.equals(personalInfo.geteMail()));
		boolean profileCheck = profile == personalInfo.profile || (profile != null && profile.equals(personalInfo.getProfile()));
		boolean hobbiesCheck = hobbies == personalInfo.hobbies || (hobbies != null && hobbies.equals(personalInfo.getHobbies()));
		
		return firstNameCheck && secondNameCheck && claimPositionCheck && eMailCheck && profileCheck && hobbiesCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (firstName == null ? 0 : firstName.hashCode());
		hash = hash * 31 + (secondName == null ? 0 : secondName.hashCode());
		hash = hash * 31 + (claimPosition == null ? 0 : claimPosition.hashCode());
		hash = hash * 31 + (eMail == null ? 0 : eMail.hashCode());
		hash = hash * 31 + (profile == null ? 0 : profile.hashCode());
		hash = hash * 31 + (hobbies == null ? 0 : hobbies.hashCode());
		return hash;
	}
	
}
