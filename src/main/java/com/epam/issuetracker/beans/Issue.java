package com.epam.issuetracker.beans;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;


public class Issue extends AbstractBean implements Comparable<Issue> {

	private Employee assignee;
	private List<Employee> assignees;
	private List<Role> roles;
	private String description;
	private StatusE status;
	private Project project;
	private Integer project_id;
	private Date psd;
	private Date ped;
	private Date asd;
	private Date aed;
	
	private List<Attachment> attachments = new ArrayList<Attachment>();

	public Issue(Integer initId, Project initProject, Employee initAsignee, String initDescription) {
		super(initId);
		this.project = initProject;
		this.assignee = initAsignee;
		this.description = initDescription;
	}

	public Issue(Integer initId, Project initProject, Employee initAsignee, String initDescription, Date initpsd,
			Date initped, Date initasd, Date initaed) {
		super(initId);
		this.project = initProject;
		this.assignee = initAsignee;
		this.description = initDescription;
		this.aed = initaed;
		this.asd = initasd;
		this.psd = initpsd;
		this.ped = initped;
	}

	
	public Issue(Integer initId, Project initProject, String initDescription, Date initpsd,
			Date initped, Date initasd, Date initaed) {
		super(initId);
		this.project = initProject;
		this.description = initDescription;
		this.aed = initaed;
		this.asd = initasd;
		this.psd = initpsd;
		this.ped = initped;
	}

	public Issue(Integer initId, Integer initProjectId, String initDescription, Date initpsd,
			Date initped, Date initasd, Date initaed) {
		super(initId);
		this.project_id = initProjectId;
		this.description = initDescription;
		this.aed = initaed;
		this.asd = initasd;
		this.psd = initpsd;
		this.ped = initped;
	}
	
	/**
	 * @return the assignee
	 */
	public Employee getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}

	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public StatusE getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int statusId) {
		this.status = StatusE.statusById(statusId);
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the psd
	 */
	public Date getPsd() {
		return psd;
	}

	/**
	 * @param psd
	 *            the psd to set
	 */
	public void setPsd(Date psd) {
		this.psd = psd;
	}

	/**
	 * @return the ped
	 */
	public Date getPed() {
		return ped;
	}

	/**
	 * @param ped
	 *            the ped to set
	 */
	public void setPed(Date ped) {
		this.ped = ped;
	}

	/**
	 * @return the asd
	 */
	public Date getAsd() {
		return asd;
	}

	/**
	 * @param asd
	 *            the asd to set
	 */
	public void setAsd(Date asd) {
		this.asd = asd;
	}

	/**
	 * @return the aed
	 */
	public Date getAed() {
		return aed;
	}

	/**
	 * @param aed
	 *            the aed to set
	 */
	public void setAed(Date aed) {
		this.aed = aed;
	}

	
	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Employee> getAssignees() {
		return assignees;
	}

	public void setAssignees(List<Employee> assignees) {
		this.assignees = assignees;
	}

	
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public String toString() {
		return "Issue " + super.getId() + ";\n" + ";" + project + ";\n" + assignee + ";" + description
				+ ";" + psd + ";\n" + asd + ";" + ped + ";\n" + aed + ";" + status + "\n\n";
	}

	public int compareTo(Issue issue) {

		if (getAsd().after(issue.getAsd())) {
			return -1;
		} else if (getAsd().before(issue.getAsd())) {
			return 1;
		} else
			return 0;
	}

}
