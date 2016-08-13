package com.epam.issuetracker.beans;

public enum StatusE {
	/**
	 * Status 'new'.
	 */
	NEW(0),
	/**
	 * Status 'open'.
	 */
	OPEN(1),
	/**
	 * Status 'in progress'.
	 */
	IN_PROGRESS(2),
	/**
	 * Status 'resolved'.
	 */
	RESOLVED(3),
	/**
	 * Status 'closed'.
	 */
	CLOSED(4);
	
	private int id;

	private String status;
	private StatusE(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * The getter for Id.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	
	public String getStatus(int id){
		for(StatusE p:StatusE.values()){
			if(p.getId()==id){
				return status=p.toString();
			}
		} return null;
		
	}
	
	public static StatusE statusById(int id){
		for(StatusE p:StatusE.values()){
			if(p.getId()==id){
				return p;
			}
		} return null;
	}
}
