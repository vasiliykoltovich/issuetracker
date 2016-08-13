package com.epam.issuetracker;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.StatusDAO.StatusDAOFactory;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Project;


public class ModifyIssue extends AbstractServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6087623135611064171L;
	private final  Logger LOGGER=LogManager.getLogger(ModifyIssue.class);

	@SuppressWarnings("unchecked")
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		try {
			
			Project project=null;
			int chosenIssueId = Integer.valueOf(request.getParameter(Web_Constants.KEY_ISSUE));
			Issue currentIssue=IssueDAOFactory.getDAO().getIssue(chosenIssueId);
			Project currentProject=currentIssue.getProject();
			Date old_ped=currentIssue.getPed();
			Date old_psd=currentIssue.getPsd();
			List<Employee> assigneeList=(List<Employee>) request.
			getSession().getAttribute(Web_Constants.KEY_ASSIGNEES);
			String description = request.getParameter(Web_Constants.COLUMN_DESCRIPTION);
			
			Issue newIssue=new Issue(chosenIssueId, project, description,
					old_psd, old_ped, null, null);
			
			newIssue.setStatus(StatusDAOFactory.getDAO().getStatus(Integer.valueOf(request.getParameter(
							Web_Constants.KEY_SELECTED_STATUS))).getId());
			
			newIssue.setProject(currentProject);
			IssueDAOFactory.getDAO().modifyIssue(newIssue,chosenIssueId);
			List<Integer> memberIds=IssueDAOFactory.getDAO().getMemberIds(newIssue,assigneeList);
			IssueDAOFactory.getDAO().modifyMemberWrapper(newIssue, memberIds);
			Integer assignmentId=IssueDAOFactory.getDAO().getAssignmentWrapper(chosenIssueId, memberIds);
			IssueDAOFactory.getDAO().modifyAssignmentWrapper(chosenIssueId, memberIds, assignmentId);
			
			LOGGER.info("Issue  with ID =" + chosenIssueId+" modified");
			moveToUrl(Web_Constants.MAIN_CONTROLLER, request, response);
		} catch (NullPointerException| DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
	
	}
	
	
}
