package com.arthurspirke.cvcreator.entity.enums;

public enum Language{

	ENGLISH("English"), RUSSIAN("Russian");
	
	private String langName;
	
	private Language(String langName){
		this.langName = langName;
	}
	
	public String getLangName(){
		return langName;
	}
	
	public static Language getLanguage(String lang){
		switch(lang){
		case "English":
			return ENGLISH;
		case "Russian":
			return RUSSIAN;
		default:
			throw new IllegalArgumentException();
		}
	}
}
