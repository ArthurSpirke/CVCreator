package com.arthurspirke.cvcreator.entity.domain;

import com.arthurspirke.cvcreator.entity.enums.LinkIcon;

public class PersonLinks {
	private final int id;
	private final int personId;
	private final LinkIcon linkType;
	private final String linkName;
	private final String state;

	public PersonLinks(int id, int personId, LinkIcon linkType,
			String linkName){
		this(id, personId, linkType, linkName, "");
	}
	
	
	public PersonLinks(int id, int personId, LinkIcon linkType,
			String linkName, String state) {
		super();
		this.id = id;
		this.personId = personId;
		this.linkType = linkType;
		this.linkName = linkName;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

	public LinkIcon getLinkType() {
		return linkType;
	}

	public String getLinkName() {
		return linkName;
	}

	public String getState() {
		return state;
	}

	
}
