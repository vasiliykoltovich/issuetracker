package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.ActivityDAO.ActivityDAOFactory;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.beans.Activity;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;

public class SubmitActivity extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9066356488311760044L;
	private final Logger LOGGER = LogManager.getLogger(SubmitActivity.class);

	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, NamingException {
		int chosenIssueId = Integer.valueOf(request.getParameter(Web_Constants.KEY_ISSUE));
		try {

			Issue chosenIssue = IssueDAOFactory.getDAO().getIssue(chosenIssueId);
			Employee assignee = EmployeeDAOFactory.getDAO()
					.getEmployee(Integer.valueOf(request.getParameter(Web_Constants.KEY_SELECTED_ASSIGNEE_PERSON)));
			
			String duration = request.getParameter(Web_Constants.KEY_DURATION);
			String description = request.getParameter(Web_Constants.KEY_COMMENT_A);
			Activity activity = new Activity(null, null, duration, description);

			activity.setEmployee(assignee);
			activity.setIssue(chosenIssue);

			Integer memberId =IssueDAOFactory.getDAO().getMemberId(assignee, chosenIssue);
			Integer assignmentid = IssueDAOFactory.getDAO().getAssignmentId(memberId, chosenIssueId);
			activity.setAssignmentId(assignmentid);
			activity.setMemberId(memberId);

			int activityId = ActivityDAOFactory.getDAO().addActivity(activity);
			LOGGER.info("Submitted activity ID = " + activityId + " by " + assignee.getFirstName() + " "
					+ assignee.getLastName());
			request.setAttribute(Web_Constants.KEY_SUBMITTED, true);
			response.sendRedirect(Web_Constants.JSP_ROOT_DASH);
			
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
	}

}
