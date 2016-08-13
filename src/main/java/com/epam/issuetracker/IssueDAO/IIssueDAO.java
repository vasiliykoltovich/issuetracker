package com.epam.issuetracker.IssueDAO;

import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.beans.Attachment;

import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Status;


public interface IIssueDAO {
	/**
	 * Method to recive from DAO collection of issues.
	 * @param sortingCollumn to sort by
	 * @param order of sorting
	 * @return List<Issue> sorted collection of Issues
	 * @throws DAOException  up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	List<Issue> getSortedIssues(String sortingCollumn, String order) throws DAOException, NamingException;
	
	
	/**
	 * Method to recive from DAO collection of issues.
	 * @return Map<Integer, Issue>
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Map<Integer, Issue> getIssues() throws DAOException, NamingException;
	List<Issue> getStatusIssues(Status status) throws DAOException, NamingException;
	
	/**
	 * Method to get Issue depend on it's Id.
	 * @param id Issue
	 * @return Issue
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Issue getIssue(Integer id) throws DAOException, NamingException;
	
	
	
	/**
	 * Method to submit attachments in DAO.
	 * @param attachment to submit
	 * @param issueId chosen
	 * @return Id of attachment
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Integer submitAttachment(Attachment attachment, Integer issueId)
			throws DAOException, NamingException;

	/**
	 * Method to get Issue attachments.
	 * @param issueId chosen
	 * @return List<Attachment> collection of Attachmnets
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	List<Attachment> getAttachments(Integer issueId) throws DAOException, NamingException;
	/**
	 * Method to add Issue attachments.
	 * @param issueId chosen
	 * @return Id of attachment
	 * @throws DAOException up to Servlet where it will be handled
	 * @throws NamingException 
	 */
	Integer addAttachment(Attachment attachment, Integer issueId) throws DAOException, NamingException;
	
	Integer addIssue(Issue issue) throws DAOException, NamingException;
	void modifyIssue(Issue issue,Integer issueId) throws DAOException, NamingException;
	List<Issue> getIssuesByUser( Employee employee) throws DAOException, NamingException;
	
	
	
	void modifyMember(Issue issue,Integer memberId) throws DAOException, NamingException;
	void modifyMemberWrapper(Issue issue, List<Integer> memberIds) throws DAOException, NamingException;
	Integer addMember(Employee user,Issue issue) throws DAOException, NamingException;
	
	
	Integer addMember(Issue issue,Integer employeeId,Integer roleId) throws DAOException, NamingException;
	List <Integer> addMemberWrapper(Issue issue)  throws DAOException, NamingException;
	
	List<Integer> getMemberIds(Issue issue,List<Employee> assigneeList) throws DAOException, NamingException;
	Integer getMemberId(Issue issue) throws DAOException, NamingException;
	Integer getMemberId(Employee assignee,Issue issue) throws DAOException, NamingException;
	
	Integer getAssignmentId(Integer memberId,Integer  issueId) throws DAOException, NamingException;
	
	void modifyAssignment(Integer memberId,Integer  issueId,Integer assignmentId) throws DAOException, NamingException;
	
	void modifyAssignmentWrapper(Integer issueId, List<Integer> memberIds,Integer assignmentId) throws DAOException, NamingException;
	
	Integer getAssignmentWrapper(Integer issueId, List<Integer> memberIds) throws DAOException, NamingException;
	Integer addAssignment(Integer memberId,Integer issueId) throws DAOException, NamingException;
	
	List<Integer> addAssignment(List<Integer> memberId,Integer issueId) throws DAOException, NamingException;
	
}
