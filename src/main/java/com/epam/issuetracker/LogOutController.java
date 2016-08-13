package com.epam.issuetracker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;


import com.epam.issuetracker.beans.Employee;
public class LogOutController extends AbstractServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4740704370315370433L;
	private final  Logger LOG=LogManager.getLogger(LogOutController.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session!=null){	
		Employee user=(Employee) request.getSession(false).getAttribute("user");
		if(user!=null){
	    LOG.info("Log Out user : "+user.getLogin()+": "+user.getFirstName()+" "+user.getLastName());
		session.invalidate();
		LOG.info("Current session is invalidated");
		response.sendRedirect(Web_Constants.REDIRECT_LOGIN_JSP);
		}else{
			LOG.info("Current user is already log out, session time is over ");
			response.sendRedirect(Web_Constants.REDIRECT_LOGIN_JSP);
		}
		} else {
			LOG.info("Current session is already invalidated");
			response.sendRedirect(Web_Constants.REDIRECT_LOGIN_JSP);
		}
		
	}

}
