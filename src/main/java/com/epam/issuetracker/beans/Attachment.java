package com.epam.issuetracker.beans;

public class Attachment extends AbstractBean {
	
	private String filename;
	private String filesize;
	private String description;
	private Integer issueId;
	

	public Attachment(Integer initId, String initFileName, String initFileSize,
			String initDescription, Integer initIssueId) {
		super(initId);
		
		this.filename = initFileName;
		this.filesize = initFileSize;
		this.description = initDescription;
		this.issueId=initIssueId;
	}


	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String fileName) {
		this.filename = fileName;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize
	 *            the filesize to set
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
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
	 * @return the issueId
	 */
	public Integer getIssueId() {
		return issueId;
	}

	/**
	 * @param issueId the issueId to set
	 */
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

}
