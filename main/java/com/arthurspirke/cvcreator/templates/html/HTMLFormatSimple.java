package com.arthurspirke.cvcreator.templates.html;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import static com.arthurspirke.cvcreator.util.AppProperties.*;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class HTMLFormatSimple {

	private static Logger log = Logger.getLogger(HTMLFormatSimple.class);

	public static Configuration getConfig() {
		Configuration cfg = new Configuration();
        String path = getPathToHTMLTemplates();
		try {
			cfg.setDirectoryForTemplateLoading(new File(path));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			return cfg;
		} catch (IOException ex) {
			log.error("Error - " + ex);
			return null;
		}

		
		
	}
}
