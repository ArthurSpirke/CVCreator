package com.arthurspirke.cvcreator.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import com.arthurspirke.cvcreator.entity.ImageUploader;
import com.arthurspirke.cvcreator.entity.JSONGenerator;

@MultipartConfig
public class FileUploader extends HttpServlet {
	Logger log = Logger.getLogger(FileUploader.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        ImageUploader uploader = new ImageUploader(request.getParts());
       
        
		response.setContentType("text/html");
		response.getWriter().print(JSONGenerator.getJSONReflectionByListStringMap(uploader.uploadImages()));


	}

}
