package com.arthurspirke.cvcreator.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.MainPersonalDAO;
import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.util.Utils;

import static com.arthurspirke.cvcreator.util.AppProperties.*;


public class ResumeGenerator {
	private Logger log = Logger.getLogger(ResumeGenerator.class);
	private PersonalInfo person;
	
	
	public ResumeGenerator(PersonalInfo personalInfo){
		this.person = personalInfo;
	}
	
	public boolean saveToDB(){
		MainPersonalDAO dao = new MainPersonalDAO();
		return dao.addNewPerson(person);
		
	}
	
	public String pdfFormat(){
         return new PDFGenerator(person, person.getTemplatePdf()).write(getPathToSaveFinalDocs());	
	}
	
	public void docFormat(){
		
	}
	
	public String htmlFormat(){
         return new HTMLGenerator(person, person.getTemplateHtml()).write();
	}
	
	
	public Map<String, Object> startAll(){
		Map<String, Object> map = new HashMap<>();
		saveToDB();
		map.put(getPdfLinkName(), Utils.getCutPath(pdfFormat()));
		//htmlFormat();
		map.put(getHtmlLinkName(), "");
		map.put(getPersonKeys()[0], person.getId());
		return map;
	}
	
	public Map<String, Object> pdfPreview(){
		Map<String, Object> map = new HashMap<>();
		PDFGenerator generator = new PDFGenerator(person, person.getTemplatePdf());
		map.put(getPreviewTempLinkName(), Utils.getCutPath(generator.write(getPathToSaveTempDocs())));
		map.put(getPersonKeys()[0], person.getId());
		return map;
	}
	
	public Map<String, Object> updatePerson(){
		Map<String, Object> map = new HashMap<>();
		PersonUpdater.updatePerson(person);
		new PDFGenerator(person, person.getTemplatePdf()).write(getPathToSaveFinalDocs());	
		map.put(getPdfLinkName(), person.getLinksToFiles().getPdfFile());
		map.put(getHtmlLinkName(), person.getLinksToFiles().getHtmlFile());
		map.put(getPersonKeys()[0], person.getId());
		return map;
	}
	
	public void sendEmail(){
        new MailService().sendMessageToUser(person);
	}


}
