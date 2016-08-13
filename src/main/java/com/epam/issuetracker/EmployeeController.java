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

public class EmployeeController extends AbstractServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8026359188523812878L;
	private final  Logger LOGGER=LogManager.getLogger(EmployeeController.class);
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		try {
			int chosenId = Integer.valueOf(request.getParameter(Web_Constants.KEY_USER));
			Employee chosenUser=EmployeeDAOFactory.getDAO().getEmployee(chosenId);
			LOGGER.info("Chosen Employee ID = " + chosenUser.getId());
			request.setAttribute(Web_Constants.KEY_EMPLOYEE, chosenUser);
			moveToUrl(Web_Constants.JSP_USER_PAGE, request, response);
			
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
		
	}

}
