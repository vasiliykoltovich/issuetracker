package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.service.SelectorHandler;

public class SearchController extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9185432993536391127L;
	private final  Logger LOGGER=LogManager.getLogger(SearchController.class);
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		try {
			
			String searched=request.getParameter("searchword");
			String[] selectorValues=request.getParameterValues("selector");
			if(searched!=null){
				request.setAttribute(Web_Constants.KEY_SEARCHWORD, searched);
				for (int i = 0; i < selectorValues.length; i++) {
					
					if(selectorValues[i].equals("1")||selectorValues[i].equals("4") ){
						LOGGER.info("Searching criteria is "+searched+", searching in projects");
						request.setAttribute(Web_Constants.KEY_PROJECTS, SelectorHandler.getProjects(searched));
					
					}
					if(selectorValues[i].equals("3")||selectorValues[i].equals("4")){
						LOGGER.info("Searching criteria is "+searched+", searching in assignees");
						request.setAttribute(Web_Constants.KEY_ASSIGN_ISSUES, SelectorHandler.getAssignIssues(searched));
					}
					if(selectorValues[i].equals("2")||selectorValues[i].equals("4")){
						LOGGER.info("Searching criteria is "+searched+", searching in statuses");
						request.setAttribute(Web_Constants.KEY_STATUS_ISSUES, SelectorHandler.getStatusIssues(searched));
						
					}
				}
				
			}
			moveToUrl(Web_Constants.JSP_SEARCH_RESULT, request, response);
		}catch (DAOException e) {
			moveToErrorUrl(e.toString(), request, response);
		}
		
	}

}
