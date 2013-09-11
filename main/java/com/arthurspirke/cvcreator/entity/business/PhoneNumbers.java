package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;
import com.arthurspirke.cvcreator.util.Utils;

public class PhoneNumbers extends Component{

	private final PhoneIcon phoneType;
    private final String phoneNumber;
    
    public PhoneNumbers(String id, String personId, PhoneIcon phoneType, String phoneNumber){
    	this(id, personId, phoneType, phoneNumber, "");
    }
    
	public PhoneNumbers(String id, String personId, PhoneIcon phoneType, String phoneNumber, String state) {
		super(id, personId, state);
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
	}


	public PhoneIcon getPhoneType() {
		return phoneType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		PhoneNumbers phoneN = (PhoneNumbers) o;
		
		boolean idCheck = id == phoneN.id || (id != null && id.equals(phoneN.getId()));
		boolean personIdCheck = personId == phoneN.personId || (personId != null && personId.equals(phoneN.getPersonId()));
		boolean phoneTypeCheck = phoneType == phoneN.phoneType || (phoneType != null && phoneType.equals(phoneN.getPhoneType()));
		boolean phoneNumberCheck = phoneNumber == phoneN.phoneNumber || (phoneNumber != null && phoneNumber.equals(phoneN.getPhoneNumber()));
		
		return idCheck && personIdCheck && phoneTypeCheck && phoneNumberCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (phoneType == null ? 0 : phoneType.hashCode());
		hash = hash * 31 + (phoneNumber == null ? 0 : phoneNumber.hashCode());
		return hash;	
	}
}
