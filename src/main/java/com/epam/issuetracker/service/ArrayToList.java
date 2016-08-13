package com.epam.issuetracker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.RoleDAO.RoleDAOFactory;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Role;

public class ArrayToList {

	
	
	public static List<Employee> arrToEmpList(String [] assignees) throws NumberFormatException, DAOException, NamingException{
		 List <String> sublistAssignee=Arrays.asList(assignees);
		List<Employee> empList=new ArrayList<Employee>();
		
		for(String subAssignee:sublistAssignee){
			
			Employee tempEmp=EmployeeDAOFactory.getDAO().getEmployee(Integer.valueOf(subAssignee));
			empList.add(tempEmp);
		}
		
		return empList;	
	}
	public static List<Role> arrToRoleList(String [] roles) throws NumberFormatException, DAOException, NamingException{
		 List <String> sublistRole=Arrays.asList(roles);
		List<Role> roleList=new ArrayList<Role>();

		for(String subRole:sublistRole){
			Role tempRole=RoleDAOFactory.getDAO().getRole(Integer.valueOf(subRole));
			roleList.add(tempRole);
		}
		
		return roleList;
		
	}
	
	
}
