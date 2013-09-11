package com.arthurspirke.cvcreator.entity.enums;

public enum TemplatePDF {
   SIMPLE, EXTENDED;
   
   public static TemplatePDF getTemplatePdfType(String stringTemplateType){
	   switch(stringTemplateType){
	   case "SIMPLE_PDF":
		   return SIMPLE;
	   case "EXTENDED_PDF":
		   return EXTENDED;
	   default:
		   throw new IllegalArgumentException();
	   }
   }
}
