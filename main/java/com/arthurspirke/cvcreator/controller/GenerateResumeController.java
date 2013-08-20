package com.arthurspirke.cvcreator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.JSONGenerator;
import com.arthurspirke.cvcreator.entityfactory.PersonalInfoFactory;
import com.arthurspirke.cvcreator.model.ResumeGenerator;

import static com.arthurspirke.cvcreator.util.ServletUtils.*;


public class GenerateResumeController extends HttpServlet {
Logger log = Logger.getLogger(GenerateResumeController.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		Map<String, String> personInfo = (Map<String, String>) request.getAttribute("person");
        Map<String, List<Map<String, String>>> additionInfo = (Map<String, List<Map<String, String>>>) request.getAttribute("additionInfo");
        
        String operationType = (String) request.getAttribute("operationType");
        
        
        ResumeGenerator generator = null;
        JSONObject jsonResponse = null;
        
        if("create".equals(operationType)){
        	generator = new ResumeGenerator(PersonalInfoFactory.newPersonalInfoToGenerateResume(personInfo, additionInfo));           
            jsonResponse = JSONGenerator.getJSONReflectionByObjectMap(generator.startAll());
        } else if("preview".equals(operationType)){
        	generator = new ResumeGenerator(PersonalInfoFactory.newPersonalInfoToPreviewResume(personInfo, additionInfo));
            jsonResponse = JSONGenerator.getJSONReflectionByObjectMap(generator.pdfPreview());
        } else if("update".equals(operationType)){
        	generator = new ResumeGenerator(PersonalInfoFactory.newPerosnalInfoToUpdateResume(personInfo, additionInfo));            
            jsonResponse = JSONGenerator.getJSONReflectionByObjectMap(generator.updatePerson());
        } else {
        	
        }

		
		setHeaders(response, "application/json");
        response.getWriter().print(jsonResponse);

	
	}

}
