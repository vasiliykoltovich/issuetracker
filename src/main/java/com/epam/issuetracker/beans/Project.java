package com.epam.issuetracker.beans;

public class Project extends AbstractBean{

	private String name;
	private String description;
	
	
	public Project(Integer initId, String initName, String initDescription) {
		super(initId);
		this.name=initName;
		this.description=initDescription;
	}

	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * @return the project_manager
	 */
	

	@Override
	public String toString() {
		return "project " + super.getId() + ";" + name;
	}
	
}
