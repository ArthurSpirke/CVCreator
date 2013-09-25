package com.arthurspirke.cvcreator.entity.enums;


public enum EducationType implements Types{
   UNIVERSITY("University"), COLLEGE("College"), SCHOOL("School");
   
   private String eduTypeName;
   
   private EducationType(String eduTypeName){
	   this.eduTypeName = eduTypeName;
   }
   
   public String getEduTypeName(){
	   return eduTypeName;
   }
   
   public static EducationType getType(String stringType){
	   switch(stringType){
	   case "University":
		   return UNIVERSITY;
	   case "College":
		   return COLLEGE;
	   case "School":
		   return SCHOOL;
	   default:
		   throw new IllegalArgumentException();
	   }
   }
   
}
