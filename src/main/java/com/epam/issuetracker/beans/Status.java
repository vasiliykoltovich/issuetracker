package com.epam.issuetracker.beans;

public class Status  extends AbstractBean{

	private String status;
	
	/**
	 * Status Constructor.
	 * @param id initial value for id transferred
	 * to Superclass constructor
	 * @param initStatus initial value of status
	 */
	public Status(Integer id, String initStatus) {
		super(id);
		this.status = initStatus;
	}

	/**
	 * Getter for status.
	 * @return status
	 */
    public String getStatus() {
		return status;
	}

    /**
	 * Setter for status.
	 * @param newStatus replace status with It's value
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	/**
	 * @return String representation of this bean class
	 */
	@Override
	public String toString() {
		return "status " + status;
	}

}
