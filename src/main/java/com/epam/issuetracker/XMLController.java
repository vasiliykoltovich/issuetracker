package com.epam.issuetracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Project;

public class XMLController extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException {
		try{
		int chosenIssueId = Integer.valueOf(request.getParameter(Web_Constants.KEY_ISSUE));
		
		Issue currentIssue=IssueDAOFactory.getDAO().getIssue(chosenIssueId);
		Project currentProject=currentIssue.getProject();
		
		@SuppressWarnings("unchecked")
		List<Employee> assigneeList=(List<Employee>) request.
				getSession().getAttribute(Web_Constants.KEY_ASSIGNEES);
		
		 response.setContentType("text/xml");
	        String reportName =  "Issue_Report.xml";     
	        response.setHeader("Content-disposition", "attachment; " +
	                "filename=" + reportName);   
	        List<String> rows = new ArrayList<String>();
	        rows.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        rows.add("<Issue>"); 
	        
	        rows.add("<ID>"); 
	        rows.add(""+chosenIssueId); 
	        rows.add("</ID>"); 
	        
	        rows.add("<Description>"); 
	        rows.add(""+currentIssue.getDescription()); 
	        rows.add("</Description>"); 
	        
	        rows.add("<Status>"); 
	        rows.add(""+currentIssue.getStatus()); 
	        rows.add("</Status>"); 
	        
	        rows.add("<PlannedStartDate>"); 
	        rows.add(""+currentIssue.getPsd()); 
	        rows.add("</PlannedStartDate>"); 
	        
	        rows.add("<PlannedEndDate>"); 
	        rows.add(""+currentIssue.getPed()); 
	        rows.add("</PlannedEndDate>"); 
	        
	        rows.add("<ActualStartDate>"); 
	        rows.add(""+currentIssue.getAsd()); 
	        rows.add("</ActualStartDate>"); 
	        
	        rows.add("<ActualEndDate>"); 
	        rows.add(""+currentIssue.getAed()); 
	        rows.add("</ActualEndDate>"); 
	        
	        rows.add("<ProjectData>"); 
	        
	        rows.add("<ProjectName>"); 
	        rows.add(""+currentProject.getName()); 
	        rows.add("</ProjectName>"); 
	        
	        rows.add("<ProjectDescription>"); 
	        rows.add(""+currentProject.getDescription()); 
	        rows.add("</ProjectDescription>"); 
	        
	        rows.add("</ProjectData>"); 
	        
	        rows.add("<Assignees>");
	        
	        for(int i=0;i<assigneeList.size();i++){
	        	
	        	rows.add("<Person"+(i+1)+">");
	        	rows.add("<FirstName>");
	        	rows.add(""+assigneeList.get(i).getFirstName()); 
	        	rows.add("</FirstName>");
	        	
	        	 rows.add("<LastName>");
		         rows.add(""+assigneeList.get(i).getLastName());
		         rows.add("</LastName>");
	        	
		         rows.add("<Position>");
		         rows.add(""+assigneeList.get(i).getPosition());
		         rows.add("</Position>");
	        	
		         rows.add("<IssueRole>");
		         rows.add(""+assigneeList.get(i).getRole());
		         rows.add("</IssueRole>");
		         rows.add("</Person"+(i+1)+">");
	        }
	        rows.add("</Assignees>");
	        rows.add("</Issue>"); 

	        Iterator<String> iter = rows.iterator();
	        while (iter.hasNext()){
	            String outputString = (String) iter.next();
	            response.getOutputStream().print(outputString);
	        }
	        response.getOutputStream().flush();   
		} catch (DAOException e) {
			
			moveToErrorUrl(e.toString(), request, response);
		}
	}

}
