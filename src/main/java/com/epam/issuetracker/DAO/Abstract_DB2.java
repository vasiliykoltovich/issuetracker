package com.epam.issuetracker.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.epam.issuetracker.DAOExceptions.DAOException;



public abstract class Abstract_DB2 {

	private static String envContextString="java:/comp/env";
	private static String envContextStringDS="jdbc/DbTomcat";
	
protected static Connection getConnection() throws  SQLException, NamingException {
		
		
		Context initContext=new InitialContext();
		Context envContext=(Context) initContext.lookup(envContextString);
		
		 DataSource ds =(DataSource) envContext.lookup(envContextStringDS);
		 Connection connection=ds.getConnection();
		 return connection;
	}
	
	protected static void closeConnection (Connection connection,
			Statement statement,
			ResultSet resultSet) throws DAOException{
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
            System.err.println("Can't close current connection" + e);
            throw new DAOException(e);
		}
		
		
	}
	
}
