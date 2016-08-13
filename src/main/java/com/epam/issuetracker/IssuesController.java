package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;

public class IssuesController extends AbstractServlet {

	
	private static final long serialVersionUID = -8316748962635123200L;
	private final  Logger LOGGER=LogManager.getLogger(IssuesController.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		
		try {
			request.setAttribute(Web_Constants.KEY_ISSUES, IssueDAOFactory.getDAO().getIssues());
			LOGGER.info("Getting issues list");
			moveToUrl(Web_Constants.JSP_ISSUES, request, response);
		} catch (DAOException e) {
			LOGGER.error(e.toString());
			moveToErrorUrl(e.toString(), request, response);
		}
		
		
	}
}
