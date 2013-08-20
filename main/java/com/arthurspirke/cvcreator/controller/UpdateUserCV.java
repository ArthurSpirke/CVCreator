package com.arthurspirke.cvcreator.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.entityfactory.PersonalInfoFactory;
import com.arthurspirke.cvcreator.util.Utils;


public class UpdateUserCV extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String id = request.getParameter("id");
	       PersonalInfo person = PersonalInfoFactory.getFullPersonFromDBById(Integer.parseInt(id));

	        
			ResourceBundle res = Utils.getResourceBundleByLang(person.getPrefLang());

	       request.setAttribute("lang", person.getPrefLang());
	       request.setAttribute("res", res);
           request.setAttribute("person", person);
           
           RequestDispatcher rd = request.getRequestDispatcher("UpdateCVInfo.jsp");
	       rd.forward(request, response);
	}
	
	

	
	

	

}
