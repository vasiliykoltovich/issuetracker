package com.epam.issuetracker;

import java.io.IOException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;
import com.epam.issuetracker.RoleDAO.RoleDAOFactory;
import com.epam.issuetracker.StatusDAO.StatusDAOFactory;
import com.epam.issuetracker.beans.Project;


public class AddIssue  extends AbstractServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7141542701055955056L;
	private final  Logger LOGGER=LogManager.getLogger(AddIssue.class);

	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
	
		
		try {
			
			Map<Integer, Project> projects = ProjectDAOFactory.getDAO().getProjects();
			request.setAttribute(Web_Constants.KEY_PROJECTS, projects);
			request.setAttribute(Web_Constants.KEY_STATUSES, StatusDAOFactory.getDAO().getStatuses());
			//request.setAttribute(Web_Constants.KEY_USERS, EmployeeDAOFactory.getDAO().getEmployees());
			request.setAttribute(Web_Constants.KEY_USERS, EmployeeDAOFactory.getDAO().getEmployeesSortedList());
			request.setAttribute(Web_Constants.KEY_ROLES, RoleDAOFactory.getDAO().getRoles());
			
			moveToUrl(Web_Constants.JSP_ADD_ISSUE, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
	
		
		
	}
}
