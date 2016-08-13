package com.epam.issuetracker.IssueDAOImplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.epam.issuetracker.DAO.Abstract_DB2;
import com.epam.issuetracker.DAO.DAOConstants;
import com.epam.issuetracker.DAOExceptions.DAOException;
import com.epam.issuetracker.EmployeeDAO.EmployeeDAOFactory;
import com.epam.issuetracker.EmployeeDAOImplementation.DBImplIEmployeeDAO;
import com.epam.issuetracker.IssueDAO.IIssueDAO;
import com.epam.issuetracker.ProjectDAO.ProjectDAOFactory;
import com.epam.issuetracker.beans.Attachment;
import com.epam.issuetracker.beans.Employee;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.Project;
import com.epam.issuetracker.beans.Status;

public class DBImplIIssueDAO extends Abstract_DB2 implements IIssueDAO {

	public DBImplIIssueDAO() {
	}

	@Override
	public List<Issue> getSortedIssues(String sortingCollumn, String order) throws DAOException, NamingException {

		List<Issue> issues = new ArrayList<Issue>();
		Map<Integer, Employee> employees = EmployeeDAOFactory.getDAO().getEmployees();

		Issue issue;
		Integer id;
		String description;
		Date psd;
		Date ped;
		Date asd;
		Date aed;
		Project project;
		int employeeId;
		Integer project_id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM issue " + " JOIN (SELECT member.project_id AS idProject,"
					+ " member.employee_id AS idEmployee from member) as mb " + " ON mb.idProject=issue.project_id "
					+ " JOIN (SELECT status.id AS idStatus from status) as st "
					+ " ON st.idStatus=issue.status_id ORDER BY " + sortingCollumn + "  " + order);
			while (rs.next()) {
				
				id = rs.getInt(DAOConstants.COLUMN_ID);
				description = rs.getString(DAOConstants.COLUMN_DESCRIPTION).trim();
				psd = rs.getDate(DAOConstants.COLUMN_PSD);
				ped = rs.getDate(DAOConstants.COLUMN_PED);
				asd = rs.getDate(DAOConstants.COLUMN_ASD);
				aed = rs.getDate(DAOConstants.COLUMN_AED);
				project_id = rs.getInt(DAOConstants.COLUMN_PROJECT_ID);

				issue = new Issue(id, project_id, description, psd, ped, asd, aed);
				project = ProjectDAOFactory.getDAO().getProject(project_id);

				issue.setStatus(rs.getInt(DAOConstants.VAR_STATUS_ID));
				issue.setProject(project);

				employeeId = rs.getInt(DAOConstants.COLUMN_EMPLOYEE_ID);
				if (!rs.wasNull()) {
					issue.setAssignee(employees.get(employeeId));
				}
				issues.add(issue);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return issues;

	}

	@Override
	public Map<Integer, Issue> getIssues() throws DAOException, NamingException {
		Map<Integer, Issue> issues = new HashMap<Integer, Issue>();

		Map<Integer, Project> projects = ProjectDAOFactory.getDAO().getProjects();

		Issue issue;
		Integer id;
		String description;
		Date psd;
		Date ped;
		Date asd;
		Date aed;
		int status_id;
		Project project;
		Integer project_id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			st = connection.createStatement();
			
			rs = st.executeQuery("SELECT * FROM issue ");
			while (rs.next()) {
				id = rs.getInt(DAOConstants.COLUMN_ID);
				description = rs.getString(DAOConstants.COLUMN_DESCRIPTION).trim();
				psd = rs.getDate(DAOConstants.COLUMN_PSD);
				ped = rs.getDate(DAOConstants.COLUMN_PED);
				asd = rs.getDate(DAOConstants.COLUMN_ASD);
				aed = rs.getDate(DAOConstants.COLUMN_AED);
				project_id = rs.getInt(DAOConstants.COLUMN_PROJECT_ID);
				status_id = rs.getInt(DAOConstants.COLUMN_STATUS_ID);
				issue = new Issue(id, project_id, description, psd, ped, asd, aed);
				project = projects.get(project_id);
				issue.setProject(project);
				issue.setStatus(status_id);

				issues.put(id, issue);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return issues;

	}

	@Override
	public Issue getIssue(Integer id) throws DAOException, NamingException {
		// TODO Auto-generated method stub
		Issue issue = null;
		String description;
		Date psd;
		Date ped;
		Date asd;
		Date aed;

		Project project;
		Integer project_id;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			ps = connection.prepareStatement("SELECT id ,description,psd,ped,asd,aed,project_id AS idIProject,"
					+ "status_id AS idIStatus FROM issue  JOIN (SELECT assignment.issue_id AS idAIssue,"
					+ "assignment.member_id AS idAMember from assignment) as asg ON asg.idAIssue=issue.id "
					+ "  JOIN (SELECT member.id AS idMember,project_id AS idMProject,employee_id as idMEmployee "
					+ "FROM member) as mb ON asg.idAMember=mb.idMember and issue.project_id=mb.idMProject "
					+ "  WHERE issue.id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(DAOConstants.COLUMN_ID);
				description = rs.getString(DAOConstants.COLUMN_DESCRIPTION);
				psd = rs.getDate(DAOConstants.COLUMN_PSD);
				ped = rs.getDate(DAOConstants.COLUMN_PED);
				asd = rs.getDate(DAOConstants.COLUMN_ASD);
				aed = rs.getDate(DAOConstants.COLUMN_AED);
				project_id = rs.getInt(DAOConstants.VAR_PROJECT_IID);
				issue = new Issue(id, project_id, description, psd, ped, asd, aed);
				project = ProjectDAOFactory.getDAO().getProject(project_id);
				issue.setProject(project);
				issue.setStatus(rs.getInt(DAOConstants.VAR_STATUS_IID));

			}
		} catch (SQLException e) {
			closeConnection(connection, ps, rs);
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return issue;
	}

	@SuppressWarnings("resource")
	@Override
	public Integer submitAttachment(Attachment attachment, Integer issueId) throws DAOException, NamingException {
		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection.prepareStatement(
					"INSERT INTO attachment (filename, filesize, description, issue_id) VALUES (?, ?, ?, ?)");
			ps.setString(index, attachment.getFilename());
			ps.setString(++index, attachment.getFilesize());
			ps.setString(++index, attachment.getDescription());
			ps.setInt(++index, issueId);
			ps.executeUpdate();

			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			}
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return id;
	}

	@Override
	public List<Attachment> getAttachments(Integer issueId) throws DAOException, NamingException {

		List<Attachment> attachments = new ArrayList<Attachment>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM attachment WHERE issue_id = ? ORDER BY filesize ASC");
			ps.setInt(1, issueId);
			rs = ps.executeQuery();
			while (rs.next()) {
				attachments.add(new Attachment(rs.getInt(DAOConstants.COLUMN_ID),
						rs.getString(DAOConstants.COLUMN_FILENAME), rs.getString(DAOConstants.COLUMN_FILESIZE),
						rs.getString(DAOConstants.COLUMN_DESCRIPTION), rs.getInt(DAOConstants.COLUMN_TASK_ID)));

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return attachments;

	}

	@SuppressWarnings({ "resource" })
	@Override
	public Integer addAttachment(Attachment attachment, Integer issueId) throws DAOException, NamingException {
		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection.prepareStatement(
					"INSERT INTO attachment (filename," + " filesize, description, issue_id) VALUES (?, ?, ?, ?)");

			ps.setString(index, attachment.getFilename());
			ps.setString(++index, attachment.getFilesize());
			ps.setString(++index, attachment.getDescription());

			ps.setInt(++index, issueId);
			ps.executeUpdate();
			}
			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			ps = connection.prepareStatement(DAOConstants.SQL_SHUTDOWN);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return id;
	}

	@SuppressWarnings("resource")
	@Override
	public Integer addIssue(Issue issue) throws DAOException, NamingException {
		Integer id = null;
		int index = 1;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection.prepareStatement(
					"INSERT INTO issue (project_id, description, psd, ped, " + "status_id) VALUES (?, ?,?, ?,?)");

			ps.setInt(index, issue.getProject_id());
			ps.setString(++index, issue.getDescription().trim());
			ps.setDate(++index, issue.getPsd());
			ps.setDate(++index, issue.getPed());
			ps.setInt(++index, issue.getStatus().getId());

			ps.executeUpdate();
			}
			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return id;
	}

	@SuppressWarnings("resource")
	@Override
	public void modifyIssue(Issue issue, Integer issueId) throws DAOException, NamingException {
		// TODO Auto-generated method stub
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			if (issue.getStatus().getId() == 2) {

				ps = connection.prepareStatement(
						"UPDATE issue SET  asd = CURDATE(),aed=0000-00-00,  description = ?, status_id =?"
								+ "  where id=?");
			

				ps.setString(index, issue.getDescription());
				ps.setInt(++index, issue.getStatus().getId());
				ps.setInt(++index, issueId);

			} else if (issue.getStatus().getId() == 3) {

				ps = connection.prepareStatement(
						"UPDATE issue SET   aed = CURDATE(), description = ?, status_id =?"
								+ "  where id=?");
				

				ps.setString(index, issue.getDescription());
				ps.setInt(++index, issue.getStatus().getId());
				ps.setInt(++index, issueId);

			} else if (issue.getStatus().getId() == 4) {

				ps = connection.prepareStatement(
						"UPDATE issue SET   aed = CURDATE(), description = ?, status_id =?"
								+ "  where id=?");
				
				ps.setString(index, issue.getDescription());
				ps.setInt(++index, issue.getStatus().getId());
				ps.setInt(++index, issueId);

			} else {
				ps = connection.prepareStatement(
						"UPDATE issue SET   aed=0000-00-00, asd=0000-00-00, description = ?, status_id =?" + "  where id=?");
				
				ps.setString(index, issue.getDescription());
				ps.setInt(++index, issue.getStatus().getId());
				ps.setInt(++index, issueId);
			}

			ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}

	}

	@Override
	public List<Issue> getIssuesByUser(Employee employee) throws DAOException, NamingException {

		List<Issue> issues = new ArrayList<Issue>();

		Issue issue;
		Integer id;
		String description;
		Date psd;
		Date ped;
		Date asd;
		Date aed;
		Project project;
		Integer project_id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT id,description,psd,ped,asd,aed,project_id AS idIProject,"
					+ "status_id AS idIStatus FROM issue  JOIN(SELECT assignment.issue_id AS idAIssue,"
					+ "assignment.member_id AS idAMember from assignment) as asg ON asg.idAIssue=issue.id "
					+ "  JOIN(SELECT member.id AS idMember,project_id AS idMProject,employee_id as idMEmployee FROM member )"
					+ " as mb   ON asg.idAMember=mb.idMember and issue.project_id=mb.idMProject "
					+ "  WHERE mb.idMEmployee = ?");
			ps.setInt(1, employee.getId());
			rs = ps.executeQuery();
			while (rs.next()) {

				id = rs.getInt(DAOConstants.COLUMN_ID);
				description = rs.getString(DAOConstants.COLUMN_DESCRIPTION).trim();
				psd = rs.getDate(DAOConstants.COLUMN_PSD);
				ped = rs.getDate(DAOConstants.COLUMN_PED);
				asd = rs.getDate(DAOConstants.COLUMN_ASD);
				aed = rs.getDate(DAOConstants.COLUMN_AED);

				project_id = rs.getInt(DAOConstants.VAR_PROJECT_IID);
				issue = new Issue(id, project_id, description, psd, ped, asd, aed);
				project = ProjectDAOFactory.getDAO().getProject(project_id);
				issue.setStatus(rs.getInt(DAOConstants.VAR_STATUS_IID));
				issue.setProject(project);
				issues.add(issue);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return issues;
	}

	
	@Override
	public List<Issue> getStatusIssues(Status status) throws DAOException, NamingException {

		List<Issue> issues = new ArrayList<Issue>();
		Issue issue;
		Integer id;
		String description;
		Date psd;
		Date ped;
		Date asd;
		Date aed;
		Project project;
		Integer project_id;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT id,description,psd,ped,asd,aed,project_id ,"
					+ "status_id  FROM issue   "
					+ "  WHERE status_id = ?");
			ps.setInt(1, status.getId());
			rs = ps.executeQuery();
			while (rs.next()) {

				id = rs.getInt(DAOConstants.COLUMN_ID);
				description = rs.getString(DAOConstants.COLUMN_DESCRIPTION).trim();
				psd = rs.getDate(DAOConstants.COLUMN_PSD);
				ped = rs.getDate(DAOConstants.COLUMN_PED);
				asd = rs.getDate(DAOConstants.COLUMN_ASD);
				aed = rs.getDate(DAOConstants.COLUMN_AED);

				project_id = rs.getInt(DAOConstants.COLUMN_PROJECT_ID);
				issue = new Issue(id, project_id, description, psd, ped, asd, aed);
				project = ProjectDAOFactory.getDAO().getProject(project_id);
				issue.setStatus(rs.getInt(DAOConstants.COLUMN_STATUS_ID));
				issue.setProject(project);
				issues.add(issue);

			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
		return issues;
	}
	
	
	@SuppressWarnings("resource")
	@Override
	public Integer addMember(Employee user, Issue issue) throws DAOException, NamingException {

		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection.prepareStatement("INSERT INTO member (project_id, employee_id, status_id)  VALUES (?,?,?)");

			ps.setInt(index, issue.getProject().getId());
			ps.setInt(++index, user.getId());
			ps.setInt(++index, issue.getStatus().getId());
			ps.executeUpdate();
			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			}
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return id;

	}

	@Override
	public void modifyMemberWrapper(Issue issue, List<Integer> memberIds) throws DAOException, NamingException {

		for (Integer mi : memberIds) {

			modifyMember(issue, mi);
		}

	}

	@Override
	public void modifyMember(Issue issue, Integer memberId) throws DAOException, NamingException {

		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			ps = connection
					.prepareStatement("UPDATE member SET  status_id = ?  where id=?");

			ps.setInt(index, issue.getStatus().getId());
			ps.setInt(++index, memberId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}

	}

	@Override
	public List<Integer> addMemberWrapper(Issue issue) throws DAOException, NamingException {
		List<Integer> memberList = new ArrayList<Integer>();
		
		try{
			if(issue.getAssignees().size()==issue.getRoles().size()){
				
				for(int i=0;i<issue.getAssignees().size();i++){
					
					int empId=issue.getAssignees().get(i).getId();
					int roleId=issue.getRoles().get(i).getId();
					int addM=addMember(issue,empId,roleId);
					memberList.add(addM);
					
				}
				return memberList;
				
			} else {
				throw new DAOException("Non equal arrays");
			}
			
		}catch (Exception e) {
			throw new DAOException(e);
		}
		

	}

	@SuppressWarnings("resource")
	@Override
	public Integer addMember(Issue issue, Integer employeeId,Integer roleId) throws DAOException, NamingException {
		// TODO Auto-generated method stub
		Integer id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection
					.prepareStatement("INSERT INTO member ( project_id,employee_id, status_id,role_id)  VALUES (?,?,?,?)");

			ps.setInt(index, issue.getProject_id());
			ps.setInt(++index, employeeId);
			ps.setInt(++index, issue.getStatus().getId());
			ps.setInt(++index, roleId);
			ps.executeUpdate();

			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			}
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return id;
	}

	@Override
	public Integer getMemberId(Employee assignee, Issue issue) throws DAOException, NamingException {
		Integer member_id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * from member JOIN (SELECT "
					+ "assignment.issue_id AS idIssue from assignment) as ass ON ass.idIssue=?"
					+ " where project_id=?  and employee_id=? and status_id=?");
			ps.setInt(index, issue.getId());
			ps.setInt(++index, issue.getProject_id());
			ps.setInt(++index, assignee.getId());
			ps.setInt(++index, issue.getStatus().getId());
			rs = ps.executeQuery();

			if (rs.next()) {
				member_id = rs.getInt(DAOConstants.COLUMN_ID);

			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return member_id;
	}

	
	@Override
	public List<Integer> getMemberIds(Issue issue, List<Employee> assigneeList) throws DAOException, NamingException {
		List<Integer> memberIds = new ArrayList<Integer>();
		
		for(Employee tempEmpl: assigneeList){
			issue.setAssignee(tempEmpl);
			Integer tempmember_id=getMemberId(issue);
			memberIds.add(tempmember_id);
		}
		return memberIds;
	}

	
	@Override
	public Integer getMemberId(Issue issue) throws DAOException, NamingException {
		Integer member_id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * from member JOIN (SELECT "
					+ "assignment.issue_id AS idIssue from assignment) as ass ON ass.idIssue=?"
					+ " where project_id=?  and employee_id=? ");
			
			ps.setInt(index, issue.getId());
			ps.setInt(++index, issue.getProject().getId());
			ps.setInt(++index, issue.getAssignee().getId());
			rs = ps.executeQuery();

			if(rs.next()) {
				member_id = rs.getInt(DAOConstants.COLUMN_ID);
				
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return member_id;
	}

	@Override
	public List<Integer> addAssignment(List<Integer> memberId, Integer issueId) throws DAOException, NamingException {
		List<Integer> assignmentIds = new ArrayList<Integer>();
		for (Integer memId : memberId) {
			int addAsssign = addAssignment(memId, issueId);
			assignmentIds.add(addAsssign);
		}
		return assignmentIds;

	}

	@SuppressWarnings("resource")
	@Override
	public Integer addAssignment(Integer memberId, Integer issueId) throws DAOException, NamingException {
		Integer assign_id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection.prepareStatement("INSERT INTO assignment (member_id, issue_id)" + "  VALUES (?,?)");

			ps.setInt(index, memberId);
			ps.setInt(++index, issueId);
			ps.executeUpdate();
			ps = connection.prepareStatement(DAOConstants.SQL_SELECT_LAST);
			rs = ps.executeQuery();
			}
			if (rs.next()) {
				assign_id = rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
		return assign_id;
	}

	@Override
	public void modifyAssignmentWrapper(Integer issueId, List<Integer> memberIds, Integer assignmentId)
			throws DAOException, NamingException {
		for (Integer mId : memberIds) {

			modifyAssignment(mId, issueId, assignmentId);
		}

	}

	@Override
	public void modifyAssignment(Integer memberId, Integer issueId, Integer assignmentId)
			throws DAOException, NamingException {
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			synchronized (DBImplIIssueDAO.class) {
			ps = connection.prepareStatement("UPDATE assignment SET member_id=?,  issue_id = ? where id=?");

			ps.setInt(index, memberId);
			ps.setInt(++index, issueId);
			ps.setInt(++index, assignmentId);
			ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}

	}

	@Override
	public Integer getAssignmentWrapper(Integer issueId, List<Integer> memberIds) throws DAOException, NamingException {

		Integer temp_Id = null;
		for (Integer mi : memberIds) {

			temp_Id = getAssignmentId(mi, issueId);

		}
		return temp_Id;
	}

	@Override
	public Integer getAssignmentId(Integer memberId, Integer issueId) throws DAOException, NamingException {
		Integer assign_id = null;
		int index = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * from assignment where member_id=?  and issue_id=?");
			ps.setInt(index, memberId);
			ps.setInt(++index, issueId);
			rs = ps.executeQuery();
			while (rs.next()) {
				assign_id = rs.getInt(DAOConstants.COLUMN_ID);

			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}

		return assign_id;

	}


}
