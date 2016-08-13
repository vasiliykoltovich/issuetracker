package com.epam.issuetracker.ProjectDAO;

import com.epam.issuetracker.ProjectDAOImplementation.DBImplIProjectDAO;

public class ProjectDAOFactory {
	private ProjectDAOFactory() {
	}

	/**
	 * Method which returns IProjectDAO implementation.
	 * @return IProjectDAO implementation
	 */
	public static IProjectDAO getDAO() {
		return new DBImplIProjectDAO();
	}
}
