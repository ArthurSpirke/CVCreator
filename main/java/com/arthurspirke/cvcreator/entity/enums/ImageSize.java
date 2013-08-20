package com.arthurspirke.cvcreator.entity.enums;

public enum ImageSize implements Types{
   _300x400, _400x500, _500x600, _600x700;
   
   
   public static ImageSize getType(String stringType){
	   switch(stringType){
	   case "300x400":
		   return _300x400;
	   case "400x500":
		   return _400x500;
	   case "500x600":
		   return _500x600;
	   case "600x700":
		   return _600x700;
	   default:
		   throw new IllegalArgumentException();
	   }
   }
   
   private int height;
   private int width;
   
   private ImageSize(){
	String stringSize = this.toString().substring(1);
	String[] hAndW = stringSize.split("x");
	height = Integer.parseInt(hAndW[0]);
	width = Integer.parseInt(hAndW[1]);
   }
   
   public int height(){
	   return height;
   }
   
   public int width(){
	   return width;
   }
   
}
