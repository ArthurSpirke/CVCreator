package com.arthurspirke.cvcreator.entityfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.domain.Certificate;
import com.arthurspirke.cvcreator.entity.enums.ImageSize;

import static com.arthurspirke.cvcreator.util.Utils.*;

public class CertificateFactory {

	
	public static List<Certificate> getCertificateList(int entityCount, int personId, List<Map<String, String>> certificateList){
		List<Certificate> returnList = new ArrayList<>();
		if(entityCount != 0){
			for(Map<String, String> map : certificateList){
	             returnList.add(newFullCertificate(personId, map));
			}
		}	
		return returnList;
	}
	
	public static Certificate newFullCertificate(int personId, Map<String, String> d){
		return new Certificate(getInteger(d.get("certId")), personId, ImageSize.getType(d.get("certSize")), d.get("pathToImages"), d.get("certSmallDesc"), d.get("certFullDesc"), d.get("certState"));
	}

}
