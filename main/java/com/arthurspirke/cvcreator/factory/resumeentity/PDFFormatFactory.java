package com.arthurspirke.cvcreator.factory.resumeentity;

import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;
import com.arthurspirke.cvcreator.templates.PDFFormat;
import com.arthurspirke.cvcreator.templates.pdf.PDFFormatExtended;
import com.arthurspirke.cvcreator.templates.pdf.PDFFormatSimple;

public class PDFFormatFactory {

     public static PDFFormat getPDFFormat(TemplatePDF templateType){
    	 switch(templateType){
    	 case SIMPLE:
    		 return new PDFFormatSimple();
    	 case EXTENDED:
    		 return new PDFFormatExtended();
    	 default:
    		 return new PDFFormatSimple();
    	 }
     }
	 
}
