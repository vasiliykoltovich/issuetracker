package com.epam.issuetracker.ActivityDAO;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.beans.Activity;
import com.epam.issuetracker.beans.Issue;

public interface IActivityDAO {

	Map<Integer,Activity> getActivities() throws DAOException, NamingException;
	Activity getActivity(Integer id) throws DAOException, NamingException;
	void updateActivity( Activity activity ) throws DAOException, NamingException;
	Integer addActivity( Activity activity) throws DAOException, NamingException;
	List<Activity> getSortedActivities(String column) throws DAOException, NamingException;
	List<Activity> getIssueActivities(Issue issue) throws DAOException, NamingException;
	
	
}
