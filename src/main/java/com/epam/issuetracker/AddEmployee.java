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


public class AddEmployee extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final  Logger LOGGER=LogManager.getLogger(AddEmployee.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		String login = request.getParameter(Web_Constants.KEY_LOGIN);
		String password = request.getParameter(Web_Constants.KEY_PASSWORD);
		String confirmation = request.getParameter(Web_Constants.KEY_CONFIRMATION);
		
		try {
			if (EmployeeDAOFactory.getDAO().checkEmployee(login) != null) {
				LOGGER.info("New employee login " + login + " is not unique!");
			
				request.setAttribute(Web_Constants.KEY_MESSAGE, Web_Constants.MESSAGE_LOGIN_UNUNIQUE);
				moveToUrl(Web_Constants.JSP_ADD_EMPLOYEE, request, response);
			} else if (!password.equals(confirmation)) {
				
				request.setAttribute(Web_Constants.KEY_MESSAGE, Web_Constants.MESSAGE_PASSWORD1);
				moveToUrl(Web_Constants.JSP_ADD_EMPLOYEE, request, response);
			
			} else {
				
				Employee newEmployee = new Employee(null, 
						request.getParameter(Web_Constants.KEY_FIRST_NAME),
						request.getParameter(Web_Constants.KEY_LAST_NAME),
						login, 
						password);
				
				newEmployee.setPosition(Integer.valueOf(request.getParameter(Web_Constants.KEY_POSITION)));
				int newUserId = EmployeeDAOFactory.getDAO().addEmployee(newEmployee);
				LOGGER.info("New employee was successfully added with Id = " + newUserId);
				moveToUrl(Web_Constants.ADMIN_CONTROLLER, request, response);
			}
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
	}

}
