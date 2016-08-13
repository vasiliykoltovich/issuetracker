package com.epam.issuetracker.beans;

public enum Position {
	/**
	 * Users Position junior. id used to identify the Position
	 */
	JUNIOR(1), /**
				 * Users Position middle. id used to identify the Position
				 */
	SOFTWARE_DEVELOPER(
			2), /**
				 * Users Position senior. id used to identify the Position
				 */
	SENIOR(3), /**
				 * Users Position teamlead. id used to identify the Position
				 */
	TEAMLEAD(4), /**
					 * Users Position PM. id used to identify the Position
					 */
	PROJECT_MANAGER(5), /**
						 * Users Position QA. id used to identify the Position
						 */
	QA(6),

	/**
	 * Users Position Admin. id used to identify the Position
	 */
	ADMIN(7);

	private int id;

	/**
	 * Position Constructor.
	 * 
	 * @param initId
	 *            initial value for id
	 */
	Position(int initId) {
		this.id = initId;
	}

	/**
	 * Getter for id.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	public static Position posById(int id){
		for(Position p:Position.values()){
			if(p.getId()==id){
				return p;
			}
		} return null;
	}
	
}
