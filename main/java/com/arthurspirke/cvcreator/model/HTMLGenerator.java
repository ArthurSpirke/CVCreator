package com.arthurspirke.cvcreator.model;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.factory.HTMLFormatFactory;

import static com.arthurspirke.cvcreator.util.AppProperties.*;
import static com.arthurspirke.cvcreator.util.ImageUtils.*;


import freemarker.template.Configuration;
import freemarker.template.SimpleCollection;
import freemarker.template.Template;

public class HTMLGenerator {
	private Logger log = Logger.getLogger(HTMLGenerator.class);
	Configuration cfg;
	PersonalInfo person;

	public HTMLGenerator(PersonalInfo person, String template) {
		super();
		this.cfg = HTMLFormatFactory.getHTMLFormat(template);
		this.person = person;
	}
	

	public String write(){
	
		Map personInfo = new HashMap();
		personInfo.put("firstName", person.getFirstName());
		personInfo.put("secondName", person.getSecondName());
		personInfo.put("position", person.getClaimPosition());
		personInfo.put("profile", person.getProfile());
		personInfo.put("hobbies", person.getHobbies());
		
		SimpleCollection skills = new SimpleCollection(person.getSkills());
		personInfo.put("skills", skills);
		
		SimpleCollection education = new SimpleCollection(person.getEducation());
		personInfo.put("education", education);
		
		SimpleCollection employmentHistory = new SimpleCollection(person.getEmploymentHistory());
		personInfo.put("employmentHistory", employmentHistory);
		
		SimpleCollection certificate = new SimpleCollection(getPathToDownloadImages(person.getCertificate()));
		personInfo.put("certificate", certificate);
		
		
		
		try{
		Writer out = new OutputStreamWriter(new FileOutputStream(getPathToSaveFinalDocs() + person.getId() + "/" + person.getId() + ".html"));
		Template tpl = cfg.getTemplate("simple.ftl");
		tpl.process(personInfo, out);
		out.flush();
		out.close();
		return person.getLinksToFiles().getHtmlFile();
		} catch(Exception ex){
			log.error("Error - " + ex);
			return null;
		}
		
		
	    
	}
	
}
