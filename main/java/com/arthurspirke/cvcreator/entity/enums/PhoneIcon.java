package com.arthurspirke.cvcreator.entity.enums;

import java.io.IOException;

import static com.arthurspirke.cvcreator.util.AppProperties.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;


public enum PhoneIcon implements Icons, Types{
     HOME_PHONE(pathToHomePhoneIcon(), "Home"), 
     MOBILE_PHONE(pathToMobilePhoneIcon(), "Mobile"), 
     SKYPE(pathToSkypeIcon(), "Skype"), 
     GOOGLE_HANGOUTS(pathToGoogleHahgOutsIcon(), "GoogleHangouts");
     
     private Image image;
     private String iconName;
     
     private PhoneIcon(String path, String iconName){
    	    this.iconName = iconName;
    	 try {
			this.image = Image.getInstance(path);
		} catch (BadElementException | IOException e) {
             //NOP
		}
     }
     
     public String getIconName(){
    	 return iconName;
     }
     
     public Image getIconImage(){
    	 return image;
     }
     
     public static PhoneIcon getPhoneIcon(String stringOfPhoneIcon){
    	 switch(stringOfPhoneIcon){
    	 case "Home":
    		 return HOME_PHONE;
    	 case "Mobile":
    		 return MOBILE_PHONE;
    	 case "Skype":
    		 return SKYPE;
    	 case "GoogleHangouts":
    		 return GOOGLE_HANGOUTS;
    	 default:
    		 throw new IllegalArgumentException();
    	 }
     }
     
     public static String[] getPhoneIconNames(){
    	 return new String[]{HOME_PHONE.getIconName(), MOBILE_PHONE.getIconName(), SKYPE.getIconName(), GOOGLE_HANGOUTS.getIconName()};
     }
     
}
