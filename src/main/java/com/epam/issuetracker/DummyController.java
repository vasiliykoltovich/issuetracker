package com.epam.issuetracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.issuetracker.ActivityDAO.ActivityDAOFactory;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.beans.Activity;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;

public class DummyController extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3004262730314947871L;

	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		HttpSession session = request.getSession(true);
		session.setAttribute(Web_Constants.KEY_PAGE,
				Integer.valueOf(request.getParameter(Web_Constants.KEY_PAGE)));
		
		if (request.getParameter(Web_Constants.KEY_ISSUES_NUMBER) != null) {

			session.setAttribute(Web_Constants.KEY_ISSUES_NUMBER,
					Integer.valueOf(request.getParameter(Web_Constants.KEY_ISSUES_NUMBER)));
			session.setAttribute(Web_Constants.KEY_START_INDEX,
					Integer.valueOf(request.getParameter(Web_Constants.KEY_START_INDEX)));

			session.setAttribute(Web_Constants.KEY_END_INDEX,
					Integer.valueOf(request.getParameter(Web_Constants.KEY_END_INDEX)));

		}

		List<Issue> issues;
		List<Activity> activities;
		String column = "_date";

		try {
			Employee user = (Employee) session.getAttribute(Web_Constants.KEY_USER);
			issues = new ArrayList<Issue>(IssueDAOFactory.getDAO().getIssuesByUser(user));
			activities = new ArrayList<Activity>(ActivityDAOFactory.getDAO().getSortedActivities(column));
			request.setAttribute(Web_Constants.KEY_ISSUES, issues);
			request.setAttribute(Web_Constants.KEY_ACTIVITIES, activities);

			moveToUrl(Web_Constants.JSP_DASH, request, response);
		} catch (DAOException e) {
			moveToErrorUrl(e.toString(), request, response);
		}
	}

}
