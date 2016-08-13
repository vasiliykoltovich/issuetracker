package com.epam.issuetracker.RoleDAO;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Role;


public interface IRoleDAO {
	Map<Integer,Role> getRoles() throws DAOException, NamingException;
	Role getRole(Integer id) throws DAOException, NamingException;
	List<Role> getAssigneeRole(Issue issue) throws DAOException, NamingException;
	List<Employee> crossRoles(List<Employee> assignees,List<Role> roles) throws DAOException, NamingException;
}
