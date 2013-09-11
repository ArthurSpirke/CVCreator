package com.arthurspirke.cvcreator.controller.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.enums.OperationType;
import com.arthurspirke.cvcreator.service.JSONGenerator;
import com.arthurspirke.cvcreator.service.PersonResumeService;
import com.arthurspirke.cvcreator.service.generators.ResumeGenerator;

import static com.arthurspirke.cvcreator.util.ServletUtils.*;

public class GenerateResumeController extends HttpServlet {
	Logger log = Logger.getLogger(GenerateResumeController.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> personInfo = (Map<String, String>) request.getAttribute("person");
		Map<String, List<Map<String, String>>> additionInfo = (Map<String, List<Map<String, String>>>) request.getAttribute("additionInfo");
        String operationType = (String) request.getAttribute("operationType");

        PersonResumeService personService = new PersonResumeService();
        Person person = personService.newPerson(personInfo, additionInfo);
        
		ResumeGenerator generator = new ResumeGenerator(person, OperationType.valueOf(operationType));
		generator.execute();
		
		Map<String, Object> responseInfo = generator.getResponseInfo();
		JSONObject jsonResponse = JSONGenerator.getJSONReflectionByObjectMap(responseInfo);

		setHeaders(response, "application/json");
		response.getWriter().print(jsonResponse);

	}

}
