package com.epam.issuetracker.StatusDAO;

import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.beans.Status;

public interface IStatusDAO {
	/**
	 * Method to recive from DAO collection of statuses.
	 * @return Map<Integer, Status>
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Map<Integer, Status> getStatuses() throws DAOException, NamingException;

	/**
	 * Method to get Status depend on it's Id.
	 * @param id status
	 * @return status
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Status getStatus(Integer id) throws DAOException, NamingException;

	/**
	 * Method to modify status.
	 * @param status temp
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	void modifyStatus(Status status) throws DAOException, NamingException;

	/**
	 * Method to check Status is it unique.
	 * @param newStatus to check
	 * @return status
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Status checkUnique(String newStatus) throws DAOException, NamingException;
	
}
