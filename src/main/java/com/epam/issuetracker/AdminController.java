package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;


public class AdminController extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final  Logger LOGGER=LogManager.getLogger(AdminController.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		
		try {
			request.setAttribute(Web_Constants.KEY_USERS, EmployeeDAOFactory.getDAO().getEmployees());
			request.setAttribute(Web_Constants.KEY_PROJECTS, ProjectDAOFactory.getDAO().getProjects());
			LOGGER.info("Getting Projects list and employee list");
			moveToUrl(Web_Constants.JSP_ADMIN, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
		
	}

}
