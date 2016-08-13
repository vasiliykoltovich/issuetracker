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

public class SubmitProject extends AbstractServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1084451220050077631L;
	private final  Logger LOGGER=LogManager.getLogger(SubmitProject.class);
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		try {
			
			int chosenProjectId=Integer.valueOf(request.getParameter(Web_Constants.KEY_PROJECT_ID));
			String name=request.getParameter(Web_Constants.KEY_NAME);
			String description=request.getParameter(Web_Constants.KEY_DESCRIPTION);
			Project editedProject=new Project(chosenProjectId, name, description);
			ProjectDAOFactory.getDAO().updateProject(editedProject);
			
				LOGGER.info("Project  with ID =" + chosenProjectId+" modified");
				moveToUrl(Web_Constants.ADMIN_CONTROLLER, request, response);
			} catch (DAOException e) {
				LOGGER.error(e.toString());
				moveToErrorUrl(e.toString(), request, response);
			}
	}

}
