package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;
import com.epam.issuetracker.beans.Project;

public class ProjectModify extends AbstractServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1505442317317713641L;
	private final  Logger LOGGER=LogManager.getLogger(ProjectModify.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		try {
			int chosenId = Integer.valueOf(request.getParameter(Web_Constants.KEY_PROJECT));
			Project chosenProject=ProjectDAOFactory.getDAO().getProject(chosenId);
			LOGGER.info("Chosen Project ID = " + chosenProject.getId());
			request.setAttribute(Web_Constants.KEY_CHOSEN_PROJECT, chosenProject);
			moveToUrl(Web_Constants.JSP_PROJECT_PAGE, request, response);
			
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
	}

}
