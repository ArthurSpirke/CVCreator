package com.arthurspirke.cvcreator.entity.enums;

public enum TemplateDOC {
    SIMPLE, EXTENDED;
    
    public static TemplateDOC getTemplateDocType(String stringTemplateType){
    	switch(stringTemplateType){
    	case "SIMPLE_DOC":
    		return SIMPLE;
    	case "EXTENDED_DOC":
    		return EXTENDED;
    	default:
    		throw new IllegalArgumentException();
    	}
    }
}
