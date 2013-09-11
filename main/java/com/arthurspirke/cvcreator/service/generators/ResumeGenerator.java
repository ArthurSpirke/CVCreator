package com.arthurspirke.cvcreator.service.generators;

import java.util.HashMap;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.enums.OperationType;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;
import com.arthurspirke.cvcreator.service.MailService;
import com.arthurspirke.cvcreator.service.PersonResumeService;
import com.arthurspirke.cvcreator.service.UpdateService;
import com.arthurspirke.cvcreator.util.Utils;

import static com.arthurspirke.cvcreator.util.AppProperties.*;


public class ResumeGenerator {
	private final Person person;
	private final OperationType operationType;
	private final Map<String, Object> responseInfo;
	
	
    public ResumeGenerator(Person person, OperationType type){
    	this.person = person;
    	this.operationType = type;
    	this.responseInfo = new HashMap<>();
    }

    
    public void execute(){
        if(operationType.equals(OperationType.GENERATE)){
           
        	saveToDB();
        	pdfFormat();

        } else if(operationType.equals(OperationType.PREVIEW)){
        	
            pdfPreview();
            
        } else if(operationType.equals(OperationType.UPDATE)){
        	
            saveToDB();
            updatePerson();
            
        } else {
        	
        	throw new IllegalArgumentException();
        }

    }
    
   
    
    public Map<String, Object> getResponseInfo(){
    	responseInfo.put(getPersonKeys()[0], person.getId());
        return responseInfo;
    }
    
	
	private void saveToDB(){
		PersonResumeService personResumeService = new PersonResumeService();
		personResumeService.save(person);
		
	}
	
	private void pdfFormat(){
		TemplatePDF templatePdf = person.getPersonalTemplates().getTemplatePDF();
		
		PDFGenerator generator = new PDFGenerator(person, templatePdf);
		String pathToNewPdfResume = generator.write(getPathToSaveFinalDocs());
		
		responseInfo.put("pdfLink", Utils.getCutPath(pathToNewPdfResume));	
	}
	
	private void docFormat(){
		
	}
	
	private void htmlFormat(){
		String templateHtml = person.getPersonalTemplates().getTemplateHTML().toString();
		HTMLGenerator generator = new HTMLGenerator(person, templateHtml); 
        String pathToNewHtmlResume = generator.write();
        responseInfo.put("KEY", pathToNewHtmlResume);
	}
	

	
	private void pdfPreview(){
		String templatePdf = person.getPersonalTemplates().getTemplatePDF().toString();
		PDFGenerator generator = new PDFGenerator(person, templatePdf);
		String pathToTempPdfResume = generator.write(getPathToSaveTempDocs());
		responseInfo.put(getPreviewTempLinkName(), Utils.getCutPath(pathToTempPdfResume));
	}
	
	private void updatePerson(){
        UpdateService service = new UpdateService(person);
		boolean isUpdatedInDB = service.update();
		
		if(isUpdatedInDB){
			
			PDFGenerator generator = new PDFGenerator(person, person.getPersonalTemplates().getTemplatePDF());	
			String pathToUpdatedPdfResume = generator.write(getPathToSaveFinalDocs());
			
			responseInfo.put(getPdfLinkName(), person.getLinksToResumeFiles().get("pdf"));
			responseInfo.put(getHtmlLinkName(), person.getLinksToResumeFiles().get("html"));
			
		} else {
			responseInfo.put("error", "Sorry");
		}
		

	}
	
	private void sendEmail(){
        new MailService().sendMessageToUser(person);
	}


}
