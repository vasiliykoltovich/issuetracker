package com.epam.issuetracker.beans;

public class Role extends AbstractBean {

	private String role;
	
	public Role(Integer initId,String initRole) {
		super(initId);
		this.role=initRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	@Override
	public String toString() {
		return "role " + role;
	}
	
}
