package com.epam.issuetracker.beans;

public abstract class AbstractBean {
	private Integer id;

	/**
	 * AbstractBean Constructor.
	 * @param initId initial value for Id
	 */
	public AbstractBean(Integer initId) {
		this.id = initId;
	}

	/**
	 * Getter for Id.
	 * @return Id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter for Id.
	 * @param newId replace Id with It's value
	 */
	public void setId(Integer newId) {
		this.id = newId;
	}
}
