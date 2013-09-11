package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.entity.enums.LinkIcon;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonLinks extends Component{
	
	private final LinkIcon linkType;
	private final String linkName;

	
	public PersonLinks(String id, String personId, LinkIcon linkType,
			String linkName){
		this(id, personId, linkType, linkName, "");
	}
	
	public PersonLinks(String id, String personId, LinkIcon linkType, String linkName, String state) {
		super(id, personId, state);
		this.linkType = linkType;
		this.linkName = linkName;
	}



	public LinkIcon getLinkType() {
		return linkType;
	}

	public String getLinkName() {
		return linkName;
	}


	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		PersonLinks link = (PersonLinks) o;	 
		
		boolean idCheck = id == link.id || (id != null && id.equals(link.getId()));
		boolean personIdCheck = personId == link.personId || (personId != null && personId.equals(link.getPersonId()));
		boolean linkTypeCheck = linkType == link.linkType || (linkType != null && linkType.equals(link.getLinkType()));
		boolean linkNameCheck = linkName == link.linkName || (linkName != null && linkName.equals(link.getLinkName()));
		
		return idCheck && personIdCheck && linkTypeCheck && linkNameCheck;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (linkType == null ? 0 : linkType.hashCode());
		hash = hash * 31 + (linkName == null ? 0 : linkName.hashCode());
		return hash;
	}
	
}
