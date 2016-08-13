package com.epam.issuetracker.ProjectDAO;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Project;



public interface IProjectDAO {

	
	/**
	 * Method to recive from DAO collection of projects.
	 * @return Map<Integer, Project>
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Map<Integer, Project> getProjects() throws DAOException, NamingException;

	/**
	 * Method to get project depend on it's Id.
	 * @param id project
	 * @return project
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Project getProject(Integer id) throws DAOException, NamingException;
	Map<Integer,Project> getProject(String name) throws DAOException, NamingException;
	List<Project> getProjects(String name) throws DAOException, NamingException;
	/**
	 * Method to add project.
	 * @param project new
	 * @param build initial build
	 * @return added project id
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Integer addProject(Project project) throws DAOException, NamingException;

	/**
	 * Method to check new Project name is Unique.
	 * @param newName to check
	 * @return project
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Project checkUnique(String newName) throws DAOException, NamingException;
	
	
	void updateProject(Project project) throws DAOException, NamingException;
	
	
	
	
	
}
