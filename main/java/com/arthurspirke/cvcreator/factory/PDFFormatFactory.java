package com.arthurspirke.cvcreator.factory;

import com.arthurspirke.cvcreator.templates.PDFFormat;
import com.arthurspirke.cvcreator.templates.pdf.PDFFormatExtended;
import com.arthurspirke.cvcreator.templates.pdf.PDFFormatSimple;

public class PDFFormatFactory {
     public final static String SIMPLE_PDF_TEMPLATE = "simple_pdf_template";
     public final static String EXTENDED_PDF_TEMPLATE = "extended_pdf_template";
  
     
     public static PDFFormat getPDFFormat(String state){
    	 switch(state){
    	 case SIMPLE_PDF_TEMPLATE:
    		 return new PDFFormatSimple();
    	 case EXTENDED_PDF_TEMPLATE:
    		 return new PDFFormatExtended();
    	 default:
    		 return new PDFFormatSimple();
    	 }
     }
	 
}
