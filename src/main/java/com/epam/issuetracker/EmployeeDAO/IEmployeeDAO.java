package com.epam.issuetracker.EmployeeDAO;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;

public interface IEmployeeDAO {

	Map<Integer,Employee> getEmployees() throws DAOException, NamingException;
	List<Issue> getEmployees(String lName) throws DAOException, NamingException;
	List<Employee> getEmployeesSortedList() throws DAOException, NamingException;
	Employee checkEmployee(String login) throws DAOException, NamingException;
	Employee getEmployee(String lname) throws DAOException, NamingException;
	Employee getEmployee(Integer id) throws DAOException, NamingException;
	Integer addEmployee(Employee employee) throws DAOException, NamingException;
	void updateEmployee(Employee employee) throws DAOException, NamingException;
	List<Employee> getAssigneeIds(Issue issue) throws DAOException, NamingException;
	
	
}
