package com.epam.issuetracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.logging.log4j.*;

import com.epam.issuetracker.ActivityDAO.ActivityDAOFactory;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.beans.Activity;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;



public class MainController extends AbstractServlet {
       
	private static final long serialVersionUID = 4870217034811210215L;
		private final  Logger LOGGER=LogManager.getLogger(MainController.class);
	
		@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			getServletContext().setAttribute(Web_Constants.KEY_NUMBER, 
					config.getInitParameter(Web_Constants.KEY_SHOW));
			LOGGER.info("Main Page Servlet initialization");
		}

	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		HttpSession session= request.getSession(true);
		session.setAttribute(Web_Constants.KEY_PAGE,
				Integer.valueOf(Web_Constants.KEY_DEFAULT_PAGE));
		
		List<Issue> issues;
		List<Activity> activities;
		String column = "_date";
		try {
				Employee user=(Employee) session.getAttribute(Web_Constants.KEY_USER);				
				issues = new ArrayList<Issue>(IssueDAOFactory.getDAO().getIssuesByUser(user));
				activities=new ArrayList<Activity>(ActivityDAOFactory.getDAO().getSortedActivities(column));
				
				request.setAttribute(Web_Constants.KEY_ISSUES, issues);
				request.setAttribute(Web_Constants.KEY_ACTIVITIES, activities);
				;
			moveToUrl(Web_Constants.JSP_DASH, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
	}

}
