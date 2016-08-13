package com.epam.issuetracker.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;
import com.epam.issuetracker.StatusDAO.StatusDAOFactory;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Status;

public class SelectorHandler {

	
	public static List<?> getProjects(String searched) throws DAOException, NamingException {
		return ProjectDAOFactory.getDAO().getProjects(searched);
	}

	public static List<?> getAssignIssues(String searched) throws DAOException, NamingException {
		Employee user=EmployeeDAOFactory.getDAO().getEmployee(searched);
		if(user==null){
			return new ArrayList<>();
		}
		return EmployeeDAOFactory.getDAO().getEmployees(searched);
	}

	public static List<?> getStatusIssues(String searched) throws DAOException, NamingException {
		Status status = StatusDAOFactory.getDAO().checkUnique(searched);
		if(status==null){
			return new ArrayList<>();
		}
		return IssueDAOFactory.getDAO().getStatusIssues(status);
	}

}
