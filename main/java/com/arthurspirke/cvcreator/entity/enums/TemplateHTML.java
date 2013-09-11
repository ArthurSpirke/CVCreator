package com.arthurspirke.cvcreator.entity.enums;

public enum TemplateHTML {
    SIMPLE, EXTENDED;
    
    public static TemplateHTML getTemplateHtmlType(String stringTemplateType){
    	switch(stringTemplateType){
    	case "SIMPLE_HTML":
    		return SIMPLE;
    	case "EXTENDED_HTML":
    		return EXTENDED;
    	default:
    		throw new IllegalArgumentException();
    	}
    
    }
}
