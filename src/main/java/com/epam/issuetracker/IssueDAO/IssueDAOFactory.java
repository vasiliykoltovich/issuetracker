package com.epam.issuetracker.IssueDAO;

import com.epam.issuetracker.IssueDAOImplementation.DBImplIIssueDAO;

public class IssueDAOFactory {

	
	private IssueDAOFactory(){}
	
	/**
	 * Method which returns IIssueDAO implementation.
	 * @return IIssueDAO implementation
	 */
	public static IIssueDAO getDAO() {
		return new DBImplIIssueDAO();
	}
}
