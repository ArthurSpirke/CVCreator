package com.arthurspirke.cvcreator.factory.resumeentity;

import com.arthurspirke.cvcreator.templates.html.HTMLFormatExtended;
import com.arthurspirke.cvcreator.templates.html.HTMLFormatSimple;

import freemarker.template.Configuration;

public class HTMLFormatFactory {
      public final static String SIMPLE_HTML_TEMPLATE = "simple_html_template";
      public final static String EXTENDED_HTML_TEMPLATE = "extended_html_template";
      
      
      public static Configuration getHTMLFormat(String template){
    	  switch(template){
    	  case SIMPLE_HTML_TEMPLATE:
               return HTMLFormatSimple.getConfig();
    	  case EXTENDED_HTML_TEMPLATE:   
    		   return HTMLFormatExtended.getConfig();
          default:
        	  return null;
    	  }
      }
	
}
