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
import com.epam.issuetracker.beans.Employee;

public class SubmitEmployee extends AbstractServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2881309920121123935L;
	private final  Logger LOGGER=LogManager.getLogger(SubmitEmployee.class);
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
	
		try {
		
		int chosenEmployeeId=Integer.valueOf(request.getParameter(Web_Constants.KEY_EMPLOYEE_ID));
		String firstName=request.getParameter(Web_Constants.KEY_FIRST_NAME);
		String lastName=request.getParameter(Web_Constants.KEY_LAST_NAME);
		String login=request.getParameter(Web_Constants.KEY_LOGIN);
		String password=request.getParameter(Web_Constants.KEY_PASSWORD);
		int positionId=Integer.valueOf(request.getParameter(Web_Constants.KEY_POSITION));
		
		Employee editedEmployee=new Employee(chosenEmployeeId,firstName,lastName,
				login,password);
		
		editedEmployee.setPosition(positionId);
		
			EmployeeDAOFactory.getDAO().updateEmployee(editedEmployee);
			LOGGER.info("Employee  with ID =" + chosenEmployeeId+" modified");
			moveToUrl(Web_Constants.ADMIN_CONTROLLER, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
	}

}
