package com.arthurspirke.cvcreator.entity.enums;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.arthurspirke.cvcreator.util.AppProperties.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public enum LinkIcon implements Icons, Types{
     MY_LINK(pathToMyLinkIcon(), "My link"),
     LINKEDIN(pathToLinkedinIcon(), "LinkedIn"),
     FACEBOOK(pathToFacebookIcon(), "Facebook"),
     GOOGLE_PLUS(pathToGooglePlusIcon(), "Google+");
          
     private Image image;
     private String iconName;
     
     private LinkIcon(String path, String iconName){
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
     
     public static LinkIcon getIconObject(String stringOfIconType){
    	 switch(stringOfIconType){
    	 case "My link":
    		 return MY_LINK;
    	 case "LinkedIn":
    		 return LINKEDIN;
    	 case "Facebook":
    		 return FACEBOOK;
    	 case "Google+":
    		 return GOOGLE_PLUS;
    	 default:
    		 throw new IllegalArgumentException();
    	 }
     }
     
     public static Map<Integer, String> getLinkIconNamesWithIds(){
    	 Map<Integer, String> map = new HashMap<>();
    	 map.put(1, MY_LINK.getIconName());
    	 map.put(2, LINKEDIN.getIconName());
    	 map.put(3, FACEBOOK.getIconName());
    	 map.put(4, GOOGLE_PLUS.getIconName());
    	 return map;
     }
	
}
