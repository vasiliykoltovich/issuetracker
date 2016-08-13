package com.epam.issuetracker.beans;

public enum RoleE {
	/**
	 * Users Role developer.
	 * id used to identify the Role
	 */
	DEVELOPER(1),
	/**
	 * Users Role key developer.
	 * id used to identify the Role
	 */
	KEY_DEVELOPER(2),
	/**
	 * Users Role lead developer.
	 * id used to identify the Role
	 */
	LEAD_DEVELOPER(5),
	/**
	 * Users Role PM.
	 * id used to identify the Role
	 */
	PROJECT_MANAGER(3),
	
	/**
	 * Users Role QA tester.
	 * id used to identify the Role
	 */
	QA_TESTER(4);
	
	
	private int id;
	/**
	 * Role Constructor.
	 * @param initId initial value for id
	 */
	RoleE(int initId) {
		this.id = initId;
	}

	/**
	 * Getter for id.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	
	public static RoleE roleById(int id){
		for(RoleE p:RoleE.values()){
			if(p.getId()==id){
				return p;
			}
		} return null;
	}
}
