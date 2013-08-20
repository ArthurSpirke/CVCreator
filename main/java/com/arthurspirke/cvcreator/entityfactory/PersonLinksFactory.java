package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.domain.PersonLinks;
import com.arthurspirke.cvcreator.entity.enums.LinkIcon;

import static com.arthurspirke.cvcreator.util.Utils.*;

public class PersonLinksFactory {

	
	public static List<PersonLinks> getPersonLinksList(int entityCount, int personId, List<Map<String, String>> personLinks){
        List<PersonLinks> returnList = new ArrayList<>();
        if(entityCount != 0){
            for(Map<String, String> map : personLinks){
            	returnList.add(newPersonLinksList(personId, map));
            }
        }   
        return returnList;
	}
	
	 public static PersonLinks newPersonLinksList(int personId, Map<String, String> d){
		 return new PersonLinks(getInteger(d.get("linkId")), personId, LinkIcon.getIconObject(d.get("linkType")), d.get("link"), d.get("linkState"));
	 }
	
}
