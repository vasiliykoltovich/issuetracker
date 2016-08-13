package com.epam.issuetracker.EmployeeDAOImplementation;

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
import com.epam.issuetracker.IssueDAO.IssueDAOFactory;
import com.epam.issuetracker.ProjectDAOImplementation.DBImplIProjectDAO;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;

public class DBImplIEmployeeDAO extends Abstract_DB2 implements IEmployeeDAO {

	public DBImplIEmployeeDAO() {

	}

	@Override
	public List<Employee> getEmployeesSortedList() throws DAOException, NamingException {
		List<Employee> employees = new ArrayList<Employee>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		Integer id;
		String firstName;
		String lastName;
		String login;
		String password;
		int positionId;
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM employee ORDER BY position_id ");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				firstName = rs.getString(DAOConstants.COLUMN_FIRST_NAME);
				lastName = rs.getString(DAOConstants.COLUMN_LAST_NAME);
				login = rs.getString(DAOConstants.COLUMN_LOGIN);
				positionId = rs.getInt(DAOConstants.COLUMN_POSITION_ID);
				password = rs.getString(DAOConstants.COLUMN_PASSWORD);
				Employee employee = new Employee(id, firstName, lastName, login, password);
				employee.setPosition(positionId);
				employees.add(employee);
			}


		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);

		}
		return employees;
	}

	
	
	@Override
	public Map<Integer, Employee> getEmployees() throws DAOException, NamingException {

		Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		Integer id;
		String firstName;
		String lastName;
		String login;
		String password;
		int positionId;
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM employee ");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				firstName = rs.getString(DAOConstants.COLUMN_FIRST_NAME);
				lastName = rs.getString(DAOConstants.COLUMN_LAST_NAME);
				login = rs.getString(DAOConstants.COLUMN_LOGIN);
				positionId = rs.getInt(DAOConstants.COLUMN_POSITION_ID);
				password = rs.getString(DAOConstants.COLUMN_PASSWORD);
				Employee employee = new Employee(id, firstName, lastName, login, password);
				employee.setPosition(positionId);
				employees.put(id, employee);
			}

			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);

		}
		return employees;

	}

	@Override
	public List<Issue> getEmployees(String lName) throws DAOException, NamingException {

		List<Issue> assignIssues = new ArrayList<Issue>();
		List<Employee> employees = new ArrayList<Employee>();
		List<Issue> tempAssignIssues = new ArrayList<Issue>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Employee employee = null;
		Integer id;
		String firstName;
		String lastName;
		String login;
		String password;
		int positionId;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM employee  WHERE login = ? OR first_name=? OR last_name=?");
			ps.setString(1, lName);
			ps.setString(2, lName);
			ps.setString(3, lName);
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				firstName = rs.getString(DAOConstants.COLUMN_FIRST_NAME);
				lastName = rs.getString(DAOConstants.COLUMN_LAST_NAME);
				login = rs.getString(DAOConstants.COLUMN_LOGIN);
				positionId = rs.getInt(DAOConstants.COLUMN_POSITION_ID);
				password = rs.getString(DAOConstants.COLUMN_PASSWORD);
				employee = new Employee(id, firstName, lastName, login, password);
				employee.setPosition(positionId);
				employees.add(employee);
			}

			for (Employee empl : employees) {
				tempAssignIssues = IssueDAOFactory.getDAO().getIssuesByUser(empl);
				for (Issue iss : tempAssignIssues) {
					iss.setAssignee(empl);
				}
				assignIssues.addAll(tempAssignIssues);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);

		}
		return assignIssues;

	}

	@Override
	public Employee checkEmployee(String login) throws DAOException, NamingException {
		Employee employee = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIEmployeeDAO.class) {
			ps = connection.prepareStatement("SELECT * FROM employee WHERE login = ?");
			ps.setString(1, login);
			rs = ps.executeQuery();
			}
			if (rs.next()) {
				employee = new Employee(rs.getInt(DAOConstants.COLUMN_ID), rs.getString(DAOConstants.COLUMN_FIRST_NAME),
						rs.getString(DAOConstants.COLUMN_LAST_NAME), rs.getString(DAOConstants.COLUMN_LOGIN),
						rs.getString(DAOConstants.COLUMN_PASSWORD));

				employee.setPosition(rs.getInt(DAOConstants.COLUMN_POSITION_ID));

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);

		}
		return employee;
	}

	@Override
	public Employee getEmployee(String lname) throws DAOException, NamingException {
		Employee employee = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM employee WHERE login = ? OR first_name=? OR last_name=?");
			ps.setString(1, lname);
			ps.setString(2, lname);
			ps.setString(3, lname);
			rs = ps.executeQuery();
			if (rs.next()) {
				employee = new Employee(rs.getInt(DAOConstants.COLUMN_ID), rs.getString(DAOConstants.COLUMN_FIRST_NAME),
						rs.getString(DAOConstants.COLUMN_LAST_NAME), rs.getString(DAOConstants.COLUMN_LOGIN),
						rs.getString(DAOConstants.COLUMN_PASSWORD));
				employee.setPosition(rs.getInt(DAOConstants.COLUMN_POSITION_ID));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return employee;
	}

	@Override
	public Employee getEmployee(Integer id) throws DAOException, NamingException {
		Employee employee = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				employee = new Employee(rs.getInt(DAOConstants.COLUMN_ID), rs.getString(DAOConstants.COLUMN_FIRST_NAME),
						rs.getString(DAOConstants.COLUMN_LAST_NAME), rs.getString(DAOConstants.COLUMN_LOGIN),
						rs.getString(DAOConstants.COLUMN_PASSWORD));
				employee.setPosition(rs.getInt(DAOConstants.COLUMN_POSITION_ID));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return employee;
	}

	@SuppressWarnings("resource")
	@Override
	public void updateEmployee(Employee employee) throws DAOException, NamingException {
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIEmployeeDAO.class) {
			ps = connection.prepareStatement(
					"UPDATE employee SET first_name = ?," + " last_name = ?, login = ? ,position_id=? WHERE id = ?");
			ps.setString(index, employee.getFirstName());
			ps.setString(++index, employee.getLastName());
			ps.setString(++index, employee.getLogin());
			ps.setInt(++index, employee.getPosition().getId());
			ps.setInt(++index, employee.getId());

			ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}

	}

	@SuppressWarnings("resource")
	@Override
	public Integer addEmployee(Employee employee) throws DAOException, NamingException {
		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIEmployeeDAO.class) {
			ps = connection.prepareStatement("INSERT INTO employee (first_name,"
					+ " last_name, login,password, position_id)  VALUES (?, ?," + " ?, ?, ?)");
			ps.setString(index, employee.getFirstName());
			ps.setString(++index, employee.getLastName());
			ps.setString(++index, employee.getLogin());
			ps.setString(++index, employee.getPassword());
			ps.setInt(++index, employee.getPosition().getId());
			ps.executeUpdate();
			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			}
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
	public List<Employee> getAssigneeIds(Issue issue) throws DAOException, NamingException {
		Employee employee = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		Set<Employee> emplSet = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement(
					"SELECT * FROM employee " + "JOIN(SELECT member.id as idMember,employee_id as idMEmployee from "
							+ "member) as mb ON mb.idMEmployee=employee.id  JOIN(SELECT "
							+ "assignment.member_id as idAMember,assignment.issue_id as idAIssue from "
							+ "assignment) as ass ON ass.idAMember=mb.idMember" + " where ass.idAIssue=? ");
			ps.setInt(1, issue.getId());
			rs = ps.executeQuery();
			while (rs.next()) {

				employee = new Employee(rs.getInt(DAOConstants.COLUMN_ID), rs.getString(DAOConstants.COLUMN_FIRST_NAME),
						rs.getString(DAOConstants.COLUMN_LAST_NAME), rs.getString(DAOConstants.COLUMN_LOGIN),
						rs.getString(DAOConstants.COLUMN_PASSWORD));
				employee.setPosition(rs.getInt(DAOConstants.COLUMN_POSITION_ID));
				employeeList.add(employee);
			}

			emplSet = new HashSet<Employee>(employeeList);
			employeeList = new ArrayList<Employee>(emplSet);

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return employeeList;
	}

	
	
}
