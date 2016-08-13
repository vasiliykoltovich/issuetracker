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

public class AddProject extends AbstractServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8728138568979839758L;
	private final  Logger LOGGER=LogManager.getLogger(AddProject.class);
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		String name = request.getParameter(Web_Constants.KEY_NAME);
		String description = request.getParameter(Web_Constants.KEY_DESCRIPTION);
		
		try {
			if (ProjectDAOFactory.getDAO().checkUnique(name) != null) {
				LOGGER.info("New project " + name + " is not unique!");
				request.setAttribute(Web_Constants.KEY_MESSAGE, Web_Constants.MESSAGE_NAME_UNUNIQUE_);
				moveToUrl(Web_Constants.JSP_ADD_PROJECT, request, response);
			
			} else {
				
				Project newProject=new Project(null, name, description);
				int newProjectId = ProjectDAOFactory.getDAO().addProject(newProject);
				LOGGER.info("New project was successfully added with Id = " + newProjectId);
				moveToUrl(Web_Constants.ADMIN_CONTROLLER, request, response);
			}
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
	}
	
	
}
