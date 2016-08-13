package com.epam.issuetracker.EmployeeDAO;

import com.epam.issuetracker.EmployeeDAOImplementation.DBImplIEmployeeDAO;

public class EmployeeDAOFactory {
	private EmployeeDAOFactory() {
	}

	/**
	 * Method which returns IUserDAO implementation.
	 * @return IUserDAO implementation
	 */
	public static IEmployeeDAO getDAO() {
		return new DBImplIEmployeeDAO();
	}
}
