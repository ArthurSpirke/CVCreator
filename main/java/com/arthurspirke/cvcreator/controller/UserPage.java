package com.arthurspirke.cvcreator.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arthurspirke.cvcreator.dblayer.LinksToFilesDAO;
import com.arthurspirke.cvcreator.entity.domain.LinksToFiles;


public class UserPage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             
		     String id = request.getParameter("id");
             
             int personId = Integer.parseInt(id);
             LinksToFilesDAO linksDAO = new LinksToFilesDAO();
             LinksToFiles links = linksDAO.getFutureLinksToFilesByPersonId(personId);
        
             
             request.setAttribute("pdf", links.getPdfFile());
             request.setAttribute("doc", links.getDocFile());
             request.setAttribute("html", links.getHtmlFile());
             request.setAttribute("id", id);
             
             
             RequestDispatcher rd = request.getRequestDispatcher("PersonalPage.jsp");
             rd.forward(request, response);
	}


}
