package com.epam.issuetracker.RoleDAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;

import com.epam.issuetracker.DAO.Abstract_DB2;
import com.epam.issuetracker.DAO.DAOConstants;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.IEmployeeDAO;
import com.epam.issuetracker.RoleDAO.IRoleDAO;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Role;

public class DBImplIRoleDAO extends Abstract_DB2 implements IRoleDAO {

	@Override
	public Map<Integer, Role> getRoles() throws DAOException, NamingException {
		Map<Integer, Role> roles = new HashMap<Integer, Role>();
		Integer id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM role");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				roles.put(id, new Role(id, rs.getString(DAOConstants.COLUMN_NAME)));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return roles;
	}

	@Override
	public Role getRole(Integer id) throws DAOException, NamingException {
		Role role = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM role WHERE id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				role = new Role(id, rs.getString(DAOConstants.COLUMN_NAME));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return role;
	}

	@Override
	public List<Role> getAssigneeRole(Issue issue) throws DAOException, NamingException {
		Role role = null;
		List<Role> roleList = new ArrayList<Role>();
		Set<Role> roleSet = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM role "
					+ "JOIN(SELECT member.id as idMember,member.role_id as idMRole,member.project_id as idMProject from "
					+ "member) as mb ON mb.idMRole=role.id  JOIN(SELECT "
					+ "assignment.member_id as idAMember,assignment.issue_id as idAIssue from "
					+ "assignment) as ass ON ass.idAMember=mb.idMember" + " where ass.idAIssue=? and mb.idMProject=?");
			ps.setInt(1, issue.getId());
			ps.setInt(2, issue.getProject().getId());
			rs = ps.executeQuery();
			while (rs.next()) {

				role = new Role(rs.getInt(DAOConstants.COLUMN_ID), rs.getString(DAOConstants.COLUMN_NAME));

				roleList.add(role);
			}

			roleSet = new HashSet<Role>(roleList);
			roleList = new ArrayList<Role>(roleSet);

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return roleList;
	}

	@Override
	public List<Employee> crossRoles(List<Employee> assignees, List<Role> roles) throws DAOException, NamingException {
		List<Employee> crossedEmpl = new ArrayList<Employee>();

		try {

			for (int i = 0; i < assignees.size(); i++) {

				Employee tempEmpl = assignees.get(i);
				int tempRoleId = roles.get(i).getId();
				tempEmpl.setRole(tempRoleId);

				crossedEmpl.add(tempEmpl);

			}
			return crossedEmpl;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
