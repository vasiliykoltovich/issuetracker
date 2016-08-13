package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.beans.Employee;

public class LoginController extends AbstractServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5625740941560113721L;
	private final Logger LOG = LogManager.getLogger(LoginController.class);

	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		
		String login = request.getParameter(Web_Constants.KEY_LOGIN);
		String password = request.getParameter(Web_Constants.KEY_PASSWORD);
		String message = null;

		try {
			login = login.trim();
			Employee user = EmployeeDAOFactory.getDAO().checkEmployee(login);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					HttpSession session = request.getSession(true);
					session.setAttribute(Web_Constants.KEY_USER, user);
					session.setMaxInactiveInterval(60*40);
					LOG.info("User " + user.getFirstName() + " " + user.getLastName() + " signed in");
				} 
				else {
					message = Web_Constants.KEY_PASSWORD;
					LOG.info("Wrong password " + password + " for user " + login);
				}
			} else if (user == null) {
				message = Web_Constants.KEY_LOGIN;
				LOG.info("Wrong login for user " + login);
			}
			if (message != null) {
				request.getSession().setAttribute(Web_Constants.KEY_LOGIN_MESSAGE, message);
			}
			response.sendRedirect(Web_Constants.JSP_ROOT_DASH);
			

		} catch (DAOException e) {
			LOG.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}

	}

}
