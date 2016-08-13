package com.epam.issuetracker.StatusDAOImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAO.Abstract_DB2;
import com.epam.issuetracker.DAO.DAOConstants;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.StatusDAO.IStatusDAO;
import com.epam.issuetracker.beans.Status;

public class DBImplIStatusDAO extends Abstract_DB2 implements IStatusDAO {

	public DBImplIStatusDAO() {
		
	}

	@Override
	public Map<Integer, Status> getStatuses() throws DAOException, NamingException {
		Map<Integer, Status> statuses = new HashMap<Integer, Status>();
		Integer id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM status");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				statuses.put(id, new Status(id, rs.getString(DAOConstants.COLUMN_NAME)));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return statuses;
	}

	@Override
	public Status getStatus(Integer id) throws DAOException, NamingException {
		Status status = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM status WHERE id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				status = new Status(id, rs.getString(DAOConstants.COLUMN_NAME));
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return status;
	}

	@SuppressWarnings("resource")
	@Override
	public void modifyStatus(Status status) throws DAOException, NamingException {
		// TODO Auto-generated method stub
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("UPDATE status SET name = ? WHERE id = ?");
			ps.setString(index, status.getStatus());
			ps.setInt(++index, status.getId());

			ps.executeUpdate();

			
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public Status checkUnique(String newStatus) throws DAOException, NamingException {
		Status status = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM status WHERE ( name = ? )");
			ps.setString(1, newStatus);
			rs = ps.executeQuery();
			if (rs.next()) {
				status = new Status(rs.getInt(DAOConstants.COLUMN_ID),
                      rs.getString(DAOConstants.COLUMN_NAME));
			}
		} catch (SQLException e) {
            throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return status;
	}

}
