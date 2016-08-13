package com.epam.issuetracker.beans;

import java.sql.Date;

public class Activity extends AbstractBean {

	private Date actDate;
	private String duration;
	private String comment;
	private Integer memberId;
	private Integer taskId;
	private Integer assignmentId;
	private Employee employee;
	private Project project;
	private Issue issue;

	public Activity(Integer initId, Date initActDate, String initDuration, String initComment) {
		super(initId);
		this.actDate = initActDate;
		this.duration = initDuration;
		this.comment = initComment;
	}

	/**
	 * @return the actDate
	 */
	public Date getActDate() {
		return actDate;
	}

	/**
	 * @param actDate
	 *            the actDate to set
	 */
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the taskId
	 */
	public Integer getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Activity" + super.getId() + actDate + ";" + duration + ";" + comment;
	}

}
