package com.epam.issuetracker.RoleDAO;

import com.epam.issuetracker.RoleDAOImplementation.DBImplIRoleDAO;

public class RoleDAOFactory {

	private RoleDAOFactory() {
	}

	/**
	 * Method which returns IUserDAO implementation.
	 * @return IUserDAO implementation
	 */
	public static IRoleDAO getDAO() {
		return new DBImplIRoleDAO();
	}
	
}
