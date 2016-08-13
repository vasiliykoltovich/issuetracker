package com.epam.issuetracker;

import java.io.IOException;
import java.util.List;

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
import com.epam.issuetracker.RoleDAO.RoleDAOFactory;
import com.epam.issuetracker.StatusDAO.StatusDAOFactory;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Role;


public class IssueController extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6569485670449896099L;
	private final  Logger LOGGER=LogManager.getLogger(IssueController.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		String jump_Address=null;
		try {
			
			int chosenId = Integer.valueOf(request.getParameter(Web_Constants.KEY_ISSUE));
			Issue chosenIssue = IssueDAOFactory.getDAO().getIssue(chosenId);
			chosenIssue.setAttachments(IssueDAOFactory.getDAO().getAttachments(chosenId));
			LOGGER.info("Chosen Issue ID = " + chosenIssue.getId());
			request.setAttribute(Web_Constants.KEY_ISSUE, chosenIssue);
			Employee currentUser=(Employee) request.getSession().getAttribute(Web_Constants.KEY_USER);
			List<Employee> assigneeList=EmployeeDAOFactory.getDAO().getAssigneeIds(chosenIssue);
			List<Role> assigneeRoles=RoleDAOFactory.getDAO().getAssigneeRole(chosenIssue);
			assigneeList=RoleDAOFactory.getDAO().crossRoles(assigneeList, assigneeRoles);
			request.getSession().setAttribute(Web_Constants.KEY_ASSIGNEES,assigneeList);
			
			if (currentUser != null) {
				request.setAttribute(Web_Constants.KEY_STATUSES, StatusDAOFactory.getDAO().getStatuses());
				request.setAttribute(Web_Constants.KEY_ACTIVITIES, 
						ActivityDAOFactory.getDAO().getIssueActivities(chosenIssue));
				jump_Address=Web_Constants.JSP_MODIFY_ISSUE;
			} else {
				jump_Address=Web_Constants.JSP_VIEW_ISSUE;
			}
			moveToUrl(jump_Address, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
	}
	

}
