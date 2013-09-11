package com.arthurspirke.cvcreator.controller.servlets;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.service.PersonResumeService;
import com.arthurspirke.cvcreator.util.Utils;


public class UpdateUserResumeController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String id = request.getParameter("id");
	       
	       PersonResumeService personService = new PersonResumeService();
	       Person person = personService.getEntity(id);

	       
	       request.setAttribute("res", Utils.getResourceBundleByLang(person.getPrefLang()));
           request.setAttribute("person", person);
           
           RequestDispatcher rd = request.getRequestDispatcher("UpdateCVInfo.jsp");
	       rd.forward(request, response);
	}
	
	

	
	

	

}
