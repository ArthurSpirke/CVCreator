package com.arthurspirke.cvcreator.entity.business;

import com.arthurspirke.cvcreator.entity.enums.TemplateDOC;
import com.arthurspirke.cvcreator.entity.enums.TemplateHTML;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonalTemplates extends Component{

    private final TemplatePDF templatePDF;
    private final TemplateHTML templateHTML;
    private final TemplateDOC templateDOC;

    
    public PersonalTemplates(String id, String personId, TemplatePDF templatePDF, TemplateHTML templateHTML, TemplateDOC templateDOC, String state){
        super(id, personId, state);
    	this.templatePDF = templatePDF;
    	this.templateHTML = templateHTML;
    	this.templateDOC = templateDOC;

    }
    
    public PersonalTemplates(String personId, String templatePDF, String templateHTML, String templateDOC, String state){
        this(Utils.getUniqueId(), personId, templatePDF, templateHTML, templateDOC, state);
 }
    
    public PersonalTemplates(String id, String personId, String templatePDF, String templateHTML, String templateDOC, String state){
        super(id, personId, state);
    	this.templatePDF = TemplatePDF.getTemplatePdfType(templatePDF);
    	this.templateHTML = TemplateHTML.getTemplateHtmlType(templateHTML);
    	this.templateDOC = TemplateDOC.getTemplateDocType(templateDOC);
    }

    

	public TemplatePDF getTemplatePDF() {
		return templatePDF;
	}

	public TemplateHTML getTemplateHTML() {
		return templateHTML;
	}

	public TemplateDOC getTemplateDOC() {
		return templateDOC;
	}

	@Override
    public boolean equals(Object o){
    	if(o == null || o.getClass() != this.getClass()) return false;
    	PersonalTemplates personTemplates = (PersonalTemplates) o;
    	
    	boolean idCheck = id == personTemplates.id || (id != null && id.equals(personTemplates.getId()));
    	boolean personIdCheck = personId == personTemplates.personId || (personId != null && personId.equals(personTemplates.getPersonId()));
    	boolean templatePdfCheck = templatePDF == personTemplates.templatePDF || (templatePDF != null && templatePDF.equals(personTemplates.getTemplatePDF()));
    	boolean templateHtmlCheck = templateHTML == personTemplates.templateHTML || (templateHTML != null && templateHTML.equals(personTemplates.getTemplateHTML()));
    	boolean templateDocCheck = templateDOC == personTemplates.templateDOC || (templateDOC != null && templateDOC.equals(personTemplates.getTemplateDOC()));
    	
    	return idCheck && personIdCheck && templatePdfCheck && templateHtmlCheck && templateDocCheck;
    }
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personId == null ? 0 : personId.hashCode());
		hash = hash * 31 + (templatePDF == null ? 0 : templatePDF.hashCode());
		hash = hash * 31 + (templateHTML == null ? 0 : templateHTML.hashCode());
		hash = hash * 31 + (templateDOC == null ? 0 : templateDOC.hashCode());
		return hash;	
	}
    
	
	
}
