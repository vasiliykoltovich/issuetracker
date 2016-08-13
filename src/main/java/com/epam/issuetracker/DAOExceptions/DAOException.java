package com.epam.issuetracker.DAOExceptions;

public class DAOException extends Exception {

	/**
	 * @author Vasiliy Koltovich
     * DAO exception class
	 */
	private static final long serialVersionUID = 2448001463440535808L;
	
	/**
	 * Default constructor to place in E-object message and to wrap real.
	 * throwable cause of exception
	 * @param message E-object message
	 * @param cause real throwable cause of exception
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Simple constructor just to wrap E-object message.
	 * @param message E-object message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Simple constructor just to wrap real throwable cause of exception.
	 * @param cause real throwable cause of exception
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	
}
