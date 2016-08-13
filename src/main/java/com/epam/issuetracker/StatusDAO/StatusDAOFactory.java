package com.epam.issuetracker.StatusDAO;

import com.epam.issuetracker.StatusDAOImplementation.DBImplIStatusDAO;

public class StatusDAOFactory {
	private StatusDAOFactory() {
	}

	/**
	 * Method which returns IStatusDAO implementation.
	 * @return IStatusDAO implementation
	 */
	public static IStatusDAO getDAO() {
		return new DBImplIStatusDAO();
	}
}
