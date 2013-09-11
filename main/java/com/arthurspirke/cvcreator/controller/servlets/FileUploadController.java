package com.arthurspirke.cvcreator.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;


import com.arthurspirke.cvcreator.service.ImageUploader;
import com.arthurspirke.cvcreator.service.JSONGenerator;

@MultipartConfig
public class FileUploadController extends HttpServlet {
	Logger log = Logger.getLogger(FileUploadController.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        ImageUploader uploader = new ImageUploader(request.getParts());
        JSONObject jsonObject = JSONGenerator.getJSONReflectionByListStringMap(uploader.uploadImages());
        
		response.setContentType("text/html");
		response.getWriter().print(jsonObject);


	}

}
