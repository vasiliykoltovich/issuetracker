package com.epam.issuetracker.ActivityDAOImplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.ActivityDAO.IActivityDAO;
import com.epam.issuetracker.DAO.Abstract_DB2;
import com.epam.issuetracker.DAO.DAOConstants;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;
import com.epam.issuetracker.beans.Activity;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Project;

public class DBImplIActivityDAO extends Abstract_DB2 implements IActivityDAO {

	@Override
	public Map<Integer, Activity> getActivities() throws DAOException, NamingException {
		Map< Integer,Activity> activities=new HashMap<Integer,Activity>();
		Connection connection=null;
		Statement st = null;
		ResultSet rs = null;
		Integer id;
		Date init_date;
		String duration;
		String comment;
		Integer memberId;
		Integer assignmentId;
		
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM activity");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				duration = rs.getString(DAOConstants.COLUMN_DURATION);
				init_date = rs.getDate(DAOConstants.COLUMN_DATE);
				comment = rs.getString(DAOConstants.COLUMN_COMMENT);
				Activity activity=new Activity(id, init_date, duration, comment);
				memberId=rs.getInt(DAOConstants.COLUMN_MEMBER_ID);
				assignmentId=rs.getInt(DAOConstants.COLUMN_ASSIGNMENT_ID);
				activity.setMemberId(memberId);
				activity.setAssignmentId(assignmentId);
				
				activities.put(id, activity);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return activities;
		
	}

	@Override
	public Activity getActivity(Integer id) throws DAOException, NamingException {
		Activity activity = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM activity WHERE id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				activity = new Activity(rs.getInt(DAOConstants.COLUMN_ID), 
						rs.getDate(DAOConstants.COLUMN_DATE),
						rs.getString(DAOConstants.COLUMN_DURATION), 
						rs.getString(DAOConstants.COLUMN_COMMENT));
				activity.setAssignmentId(rs.getInt(DAOConstants.COLUMN_ASSIGNMENT_ID));
				activity.setMemberId(rs.getInt(DAOConstants.COLUMN_MEMBER_ID));
				
				
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return activity;
	}

	@SuppressWarnings("resource")
	@Override
	public void updateActivity(Activity activity) throws DAOException, NamingException {
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("UPDATE activity SET _date = NOW,"
					+ " duration = ?, comment = ?,member_id = ?,"
					+ "assignment_id = ?  WHERE id = ?");
			ps.setDate(index, activity.getActDate());
			ps.setString(++index, activity.getDuration());
			ps.setString(++index, activity.getComment());
			ps.setInt(++index, activity.getMemberId());
			ps.setInt(++index, activity.getAssignmentId());
			ps.setInt(++index, activity.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
	        throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		
	}

	@SuppressWarnings("resource")
	@Override
	public Integer addActivity(Activity activity) throws DAOException, NamingException {
		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("INSERT INTO activity (_date,"
					+ " duration, comment,member_id,assignment_id) VALUES (CURDATE(), ?, ?,?,?)");
			
			
			ps.setString(index, activity.getDuration());
			ps.setString(++index, activity.getComment());
			ps.setInt(++index, activity.getMemberId());
			ps.setInt(++index, activity.getAssignmentId());
			ps.executeUpdate();

			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}

			
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return id;
	}

	@Override
	public List<Activity> getSortedActivities(String column) throws DAOException, NamingException {
		List<Activity> activities =new ArrayList<Activity>();
		Integer id;
		String comment;
		Date _date;
		String duration;
		Integer member_id;
		Integer issue_id;
		Integer assignment_id;
		Integer project_id;
		Integer employee_id;
		Project tempProject;
		Employee tempEmployee;
		Activity tempActivity;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		

		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM activity  "
					+ "   JOIN "
					+ "(SELECT member.employee_id AS idEmployee,"
					+ "member.project_id AS idProject,member.id AS idMember FROM member) AS mb"
					+ " ON mb.idMember=activity.member_id "
					+ " ORDER BY "+ column + " DESC");
			while (rs.next()) {

				id = rs.getInt(DAOConstants.COLUMN_ID);
				_date=rs.getDate(DAOConstants.COLUMN_DATE);
				duration=rs.getString(DAOConstants.COLUMN_DURATION);
				comment=rs.getString(DAOConstants.COLUMN_COMMENT);
				member_id=rs.getInt(DAOConstants.VAR_MEMBER_ID);
				project_id=rs.getInt(DAOConstants.VAR_PROJECT_ID);
				employee_id=rs.getInt(DAOConstants.VAR_EMPLOYEE_ID);
				
                tempActivity=new Activity(id, _date, duration, comment);
				
                tempEmployee=EmployeeDAOFactory.getDAO().getEmployee(employee_id);
                tempProject=ProjectDAOFactory.getDAO().getProject(project_id);
                
                tempActivity.setEmployee(tempEmployee);
                tempActivity.setProject(tempProject);
                
				activities.add(tempActivity);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return activities;

		
	}

	@Override
	public List<Activity> getIssueActivities(Issue issue) throws DAOException, NamingException {
		List<Activity> activities =new ArrayList<Activity>();
		Integer id;
		String comment;
		Date _date;
		String duration;
		Integer member_id;
		Integer assignment_id;
		Integer project_id;
		Integer employee_id;
		Project tempProject;
		Employee tempEmployee;
		Activity tempActivity;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM activity  "
					+ "  JOIN (SELECT assignment.member_id AS idMember,assignment.issue_id AS idIssue,"
					+ "assignment.id as idAssignment "
					+ " FROM assignment) AS assgn "
					+ " ON assgn.idMember=activity.member_id AND assgn.idAssignment=activity.assignment_id "
					+ "  JOIN(SELECT member.employee_id as idEmployee,member.id as idMMember from member) as mb "
					+ " ON mb.idMMember=activity.member_id "
					+ "WHERE assgn.idIssue=? ORDER BY _date  DESC");
			
			
             ps.setInt(1, issue.getId());
			rs = ps.executeQuery();
			
			while (rs.next()) {

				id = rs.getInt(DAOConstants.COLUMN_ID);
				_date=rs.getDate(DAOConstants.COLUMN_DATE);
				duration=rs.getString(DAOConstants.COLUMN_DURATION).trim();
				comment=rs.getString(DAOConstants.COLUMN_COMMENT).trim();
				
				employee_id=rs.getInt(DAOConstants.VAR_EMPLOYEE_ID);				
                tempActivity=new Activity(id, _date, duration, comment);			
                tempEmployee=EmployeeDAOFactory.getDAO().getEmployee(employee_id);
                tempActivity.setEmployee(tempEmployee);               
				activities.add(tempActivity);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return activities;
	}

}
