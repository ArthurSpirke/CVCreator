package com.arthurspirke.cvcreator.entityfactory;

import com.arthurspirke.cvcreator.entity.domain.LinksToFiles;

import static com.arthurspirke.cvcreator.util.AppProperties.*;

public class LinksToFilesFactory {
   
	
	public static LinksToFiles getLinksToFilesEntity(int personId){
		String path = getPathToSaveFinalDocs();//TODO: check this!
		String pdf = "ser.optivisions.ru/docs/" + personId + "/" + personId + ".pdf";
		String doc = "ser.optivisions.ru/docs/" + personId + "/" + personId + ".doc";
		String html = "ser.optivisions.ru/docs/" + personId + "/" + personId + ".html";
		
		
		return new LinksToFiles(personId, pdf, doc, html);
	}
	
}
