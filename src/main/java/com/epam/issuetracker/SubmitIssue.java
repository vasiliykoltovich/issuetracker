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
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;
import com.epam.issuetracker.StatusDAO.StatusDAOFactory;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Project;
import com.epam.issuetracker.beans.Role;
import com.epam.issuetracker.service.ArrayToList;
import com.epam.issuetracker.service.StrToDateConverter;



public class SubmitIssue extends AbstractServlet {

	private static final long serialVersionUID = 5470563910275119818L;
	
	private final  Logger LOGGER=LogManager.getLogger(SubmitIssue.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		
		try {
			String[] subsetAssignees = request.getParameterValues(Web_Constants.KEY_SELECTED_ASSIGNEE);
            List <Employee> employeeList=ArrayToList.arrToEmpList(subsetAssignees);
            
            String[] subsetRoles = request.getParameterValues(Web_Constants.KEY_SELECTED_ROLE);
            List <Role> employeeRoleList=ArrayToList.arrToRoleList(subsetRoles);
            
			int chosenAssignee=Integer.valueOf(request.getParameter(Web_Constants.KEY_SELECTED_ASSIGNEE));
			Employee chosenUser=EmployeeDAOFactory.getDAO().getEmployee(chosenAssignee);
			
			Project project=ProjectDAOFactory.getDAO().getProject
			(Integer.valueOf(request.getParameter(Web_Constants.KEY_SELECTED_PROJECT)));
			
			String description = request.getParameter(Web_Constants.COLUMN_DESCRIPTION);
			String spsd=request.getParameter(Web_Constants.PSD);
			String sped=request.getParameter(Web_Constants.PED);
			Date new_psd=StrToDateConverter.convertStringToDate(spsd);
			Date new_ped=StrToDateConverter.convertStringToDate(sped);
			
			Issue newIssue=new Issue(null, project.getId(), description,
					new_psd, new_ped, null, null);
			newIssue.setStatus(StatusDAOFactory.getDAO().getStatus(Integer.valueOf(request.getParameter(
							Web_Constants.KEY_SELECTED_STATUS))).getId());
			newIssue.setAssignee(chosenUser);
			newIssue.setAssignees(employeeList);
			newIssue.setRoles(employeeRoleList);
			newIssue.setProject(project);
			
			int insertedId=IssueDAOFactory.getDAO().addIssue(newIssue);
			List<Integer>memberList=IssueDAOFactory.getDAO().addMemberWrapper(newIssue);
			List<Integer>assignList=IssueDAOFactory.getDAO().addAssignment(memberList, insertedId);
		
			LOGGER.info("New issue submited with ID = " + insertedId);
			
			moveToUrl(Web_Constants.MAIN_CONTROLLER, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
		
		
	}

}
