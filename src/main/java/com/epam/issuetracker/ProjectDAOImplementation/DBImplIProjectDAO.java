package com.epam.issuetracker.ProjectDAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAO.Abstract_DB2;
import com.epam.issuetracker.DAO.DAOConstants;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.ProjectDAO.IProjectDAO;
import com.epam.issuetracker.beans.Project;

public class DBImplIProjectDAO extends Abstract_DB2 implements IProjectDAO {

	
	public DBImplIProjectDAO() {
		
	}

	@Override
	public Map<Integer, Project> getProjects() throws DAOException, NamingException {
		Map<Integer, Project> projects = new HashMap<Integer, Project>();
		Project project;
		Integer id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM project");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				project = new Project(id, 
						rs.getString(DAOConstants.COLUMN_NAME),
						rs.getString(DAOConstants.COLUMN_DESCRIPTION));
						
				projects.put(id, project);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return projects;
	}

	@Override
	public Project getProject(Integer id) throws DAOException, NamingException {
		Project project = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM project WHERE  id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				project = new Project(id, 
					rs.getString(DAOConstants.COLUMN_NAME),
					rs.getString(DAOConstants.COLUMN_DESCRIPTION));
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return project;
	}

	@Override
	public Map<Integer,Project> getProject(String name) throws DAOException, NamingException {
		Project project = null;
		int id=0;
		Map<Integer,Project> projects=new HashMap<Integer,Project>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM project WHERE  name = ? ");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				id=rs.getInt(DAOConstants.COLUMN_ID);
				project = new Project(id, 
					rs.getString(DAOConstants.COLUMN_NAME),
					rs.getString(DAOConstants.COLUMN_DESCRIPTION));
				projects.put(id,project);
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return projects;
	}
	

	@Override
	public List<Project> getProjects(String name) throws DAOException, NamingException {
		Project project = null;
		int id=0;
		List<Project> projects=new ArrayList<Project>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM project WHERE  name = ? ");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				id=rs.getInt(DAOConstants.COLUMN_ID);
				project = new Project(id, 
					rs.getString(DAOConstants.COLUMN_NAME),
					rs.getString(DAOConstants.COLUMN_DESCRIPTION));
				projects.add(project);
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return projects;
	}

	
	@SuppressWarnings("resource")
	@Override
	public Integer addProject(Project project) throws DAOException, NamingException {
		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIProjectDAO.class) {
			ps = connection.prepareStatement("INSERT INTO project (name, description) VALUES (?, ?)");
			ps.setString(index, project.getName());
			ps.setString(++index, project.getDescription());
		
			ps.executeUpdate();
			}
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
	public Project checkUnique(String newName) throws DAOException, NamingException {
		Project project = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIProjectDAO.class) {
				ps = connection.prepareStatement("SELECT * FROM project WHERE  name = ? ");
				ps.setString(1, newName);
				rs = ps.executeQuery();
			}
			
			if (rs.next()) {
				project = new Project(rs.getInt(DAOConstants.COLUMN_ID), 
						rs.getString(DAOConstants.COLUMN_NAME),
						rs.getString(DAOConstants.COLUMN_DESCRIPTION));
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return project;
	}

	

	@Override
	public void updateProject(Project project) throws DAOException, NamingException {
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIProjectDAO.class) {
			ps = connection.prepareStatement("UPDATE project SET name = ?,"
					+ " description=? WHERE id = ?");
			ps.setString(index, project.getName());
			ps.setString(++index, project.getDescription());
			
			ps.setInt(++index, project.getId());
			
			ps.executeUpdate();
			}
			
		} catch (SQLException e) {
	        throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		
		
	}

}
