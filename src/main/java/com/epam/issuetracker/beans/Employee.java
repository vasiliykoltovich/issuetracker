package com.epam.issuetracker.beans;

public class Employee extends AbstractBean {
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private Position position;
	private RoleE role;
	

	/**
	 * User Constructor.
	 * 
	 * @param id
	 *            initial value for id transferred to Superclass constructor
	 * @param initFirstName
	 *            initial value of firstName
	 * @param initLastName
	 *            initial value of lastName
	 * @param initPassword
	 *            initial value of password
	 */
	public Employee(Integer initId, String initFirstName, String initLastName, String initLogin, String initPassword) {
		super(initId);
		this.firstName = initFirstName;
		this.lastName = initLastName;
		this.login = initLogin;
		this.password = initPassword;

	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
	 * @return the positionId
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPosition(int positionId) {
		this.position = Position.posById(positionId);
		
	}

	public RoleE getRole() {
		return role;
	}

	public void setRole(int roleId) {
		this.role = RoleE.roleById(roleId);
	}

	/**
	 * @return String representation of this bean class
	 */
	@Override
	public String toString() {
		return "Employee  " + super.getId() + ";" + firstName + ";" + lastName + ";" + login;
	}
}
