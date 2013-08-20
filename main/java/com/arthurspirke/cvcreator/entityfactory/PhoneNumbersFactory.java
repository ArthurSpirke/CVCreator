package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.domain.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;

import static com.arthurspirke.cvcreator.util.Utils.*;

public class PhoneNumbersFactory {
	public static List<PhoneNumbers> getPhoneNumbersList(int entityCount, int personId, List<Map<String, String>> phoneNumbers){
		List<PhoneNumbers> returnList = new ArrayList<>();
		
		if(entityCount != 0){
			for(Map<String, String> map : phoneNumbers){
				returnList.add(newPhoneNumbers(personId, map));	
			}	
		}

		return returnList;
	}
	
	public static PhoneNumbers newPhoneNumbers(int personId, Map<String, String> d){
		  return new PhoneNumbers(getInteger(d.get("phId")), personId, PhoneIcon.getPhoneIcon(d.get("phoneType")), d.get("number"), d.get("phState"));
	}
}
