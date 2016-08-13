package com.epam.issuetracker.AdminFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.Web_Constants;
import com.epam.issuetracker.beans.Employee;

public class AuthentificationFilter implements Filter {
	private final Logger LOG = LogManager.getLogger(AuthentificationFilter.class);
	//private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("Start filtering admin access");
	//	this.config = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		Employee currentUser = (Employee) req.getSession().getAttribute(Web_Constants.KEY_USER);
        
		if (currentUser == null) {
			LOG.info("Unauthorized access request");
			// session.invalidate();

			RequestDispatcher rd = request.getRequestDispatcher(Web_Constants.JUMP_LOGIN_JSP);
			rd.forward(request, response);
		} else {
			
			switch (currentUser.getPosition()) {

			case ADMIN:

				RequestDispatcher rd1 = request.getRequestDispatcher(Web_Constants.ADMIN_CONTROLLER);
				rd1.forward(request, response);
				LOG.info(" Admin Access Granted");
				break;

			default:
				
				RequestDispatcher rd2 = request.getRequestDispatcher(Web_Constants.MAIN_CONTROLLER);
				rd2.forward(request, response);
			}

			chain.doFilter(request, response);

		}

	}

	@Override
	public void destroy() {

	}

}
