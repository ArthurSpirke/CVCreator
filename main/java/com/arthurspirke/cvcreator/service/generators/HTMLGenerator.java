package com.arthurspirke.cvcreator.service.generators;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.factory.resumeentity.HTMLFormatFactory;

import static com.arthurspirke.cvcreator.util.AppProperties.*;
import static com.arthurspirke.cvcreator.util.ImageUtils.*;


import freemarker.template.Configuration;
import freemarker.template.SimpleCollection;
import freemarker.template.Template;

public class HTMLGenerator {
	private Logger log = Logger.getLogger(HTMLGenerator.class);
	Configuration cfg;
	Person person;

	public HTMLGenerator(Person person, String template) {
		super();
		this.cfg = HTMLFormatFactory.getHTMLFormat(template);
		this.person = person;
	}
	

	public String write(){
	
		Map personInfo = new HashMap();
		
		PersonalInfo personalInfo = person.getPersonalInfo();
		personInfo.put("firstName", personalInfo.getFirstName());
		personInfo.put("secondName", personalInfo.getSecondName());
		personInfo.put("position", personalInfo.getClaimPosition());
		personInfo.put("profile", personalInfo.getProfile());
		personInfo.put("hobbies", personalInfo.getHobbies());
		
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
		return person.getLinksToResumeFiles().get("html");
		} catch(Exception ex){
			log.error("Error - " + ex);
			return null;
		}
		
		
	    
	}
	
}
