package com.epam.issuetracker.ActivityDAO;

import com.epam.issuetracker.ActivityDAOImplementation.DBImplIActivityDAO;
import com.epam.issuetracker.EmployeeDAO.IEmployeeDAO;
import com.epam.issuetracker.EmployeeDAOImplementation.DBImplIEmployeeDAO;

public class ActivityDAOFactory {
	private ActivityDAOFactory() {
	}

	/**
	 * Method which returns IUserDAO implementation.
	 * @return IUserDAO implementation
	 */
	public static IActivityDAO getDAO() {
		return new DBImplIActivityDAO();
	}
}
