package com.epam.issuetracker;

public class Web_Constants {

	public static final String KEY_LOGIN="login";
	public static final String KEY_PASSWORD="password";
	public static final String KEY_FIRST_NAME="firstName";
	public static final String KEY_LAST_NAME="lastName";
	public static final String KEY_POSITION="position";
	public static final String KEY_CONFIRMATION="confirmation";
	public static final String KEY_USER="user";
	public static final String KEY_EMPLOYEE="employee";
	public static final String KEY_PROJECT="project";
	public static final String KEY_PROJECT_ID="projectId";
	
	public static final String KEY_CHOSEN_PROJECT="chproject";
	public static final String KEY_NAME="name";
	public static final String KEY_DESCRIPTION="description";
	
	public static final String KEY_STATUSES="statuses";
	public static final String KEY_USERS="users";
	public static final String KEY_ROLES="roles";
	public static final String KEY_ASSIGN_ISSUES="assignissues";
	public static final String KEY_STATUS_ISSUES="statusIssues";
	public static final String KEY_SEARCHWORD="searchword";
	
	public static final String KEY_ISSUES="issues";
	public static final String KEY_ISSUE="issue";
	public static final String KEY_ACTIVITIES="activities";
	public static final String KEY_ASSIGNEES="assignees";
	public static final String KEY_MEMBER_ROLES="memberRoles";
	
	public static final String KEY_PROJECTS="projects";
	public static final String KEY_SELECTED_PROJECT="selectedProject";
	public static final String KEY_SELECTED_ASSIGNEE="selectedAssignee";
	public static final String KEY_SELECTED_ROLE="selectedRole";
	public static final String KEY_SELECTED_STATUS="selectedStatus";
	public static final String KEY_SELECTED_ASSIGNEE_ACTIVITY="selectedAssigneeA";
	public static final String KEY_SELECTED_ASSIGNEE_PERSON="assigneeAct";
	public static final String KEY_EMPLOYEE_ID="employeeId";
	
	public static final String MESSAGE_LOGIN_UNUNIQUE = "login.ununique";
	public static final String KEY_MESSAGE_CHANGE = "messageChange";
	
	public static final String MESSAGE_PASSWORD1 = "user.password.notEqual";
	
	public static final String MESSAGE_PASSWORD2 = "user.password.regExp";
	public static final String REGEXP_PASSWORD = "[\\w-.,;:!?@%$]{5,}";
	
	public static final String REDIRECT_MAIN_CONTROLLER="/MainController";
	public static final String REDIRECT_LOGIN_JSP="./loginpage.jsp";
	public static final String MAIN_CONTROLLER="/MainController";
	public static final String JUMP_MAIN_CONTROLLER="/MainController";
	public static final String KEY_MESSAGE="message";
	public static final String KEY_LOGIN_MESSAGE="messageL";
	public static final String MESSAGE_NAME_UNUNIQUE_="project name is not unique";
	public static final String JUMP_LOGIN_CONTROLLER="/LogInController";
	public static final String KEY_ERROR="error";
	public static final String JUMP_LOGIN_PAGE="/loginpage.jsp";
	
	public static final String JUMP_LOGIN_JSP="/loginpage.jsp";
	public static final String JUMP_CONTROLLER_LOGIN="/LogInController";
	
	public static final String JUMP_LOGIN="/LogInController";
	public static final String ADMIN_CONTROLLER="/AdminController";
	public static final String JSP_ROOT_DASH="./dash.jsp";
	
	public static final String JSP_DASH="/dash.jsp";
	public static final String ISSUE_CONTROLLER="/IssueController";
	
	public static final String JSP_ERROR="/error.jsp";
	public static final String JSP_PROJECTS="/projects.jsp";
	public static final String JSP_ISSUES="/issues.jsp";
	public static final String JSP_MODIFY_ISSUE="/modifyissue.jsp";
	public static final String JSP_ROOT_MODIFY_ISSUE="./modifyissue.jsp";
	public static final String JSP_VIEW_ISSUE="/viewissue.jsp";
	public static final String JSP_ADD_ISSUE="/addissue.jsp";
	public static final String JSP_ADD_EMPLOYEE="/addEmployee.jsp";
	public static final String JSP_ADD_PROJECT="/addProject.jsp";
	public static final String JSP_ADMIN="/adminpage.jsp";
	public static final String JSP_USER_PAGE="/userPage.jsp";
	public static final String JSP_PROJECT_PAGE="/projectPage.jsp";
	public static final String JSP_SEARCH_RESULT="/searchresult.jsp";
	
	public static final int KILO = 1024;
	public static final int HUNDRED = 100;
	
	public static final String KEY_NUMBER="number";
	public static final String KEY_SHOW="show";
	
	public static final String KEY_ISSUES_NUMBER="issuesNumber";
	public static final String KEY_START_INDEX="startIndex";
	public static final String KEY_END_INDEX="endIndex";
	public static final String KEY_PAGE="page";
	public static final String KEY_DEFAULT_PAGE="1";
	
	public static final String KEY_TEMP_UPLOAD="tempUploadDirectory";
	public static final String  KEY_DESTINATION_UPLOAD="destinationUploadDirectory";
	
	public static final String  KEY_FILE_DESCRIPTION="fileDescription";
	public static final String KEY_ATTACHMENT_NAME="attachmentName";
	public static final String KEY_ATTACHMENT_ID="attachmentId";
	
	public static final String KEY_SUBMITTED="submitted";
	
	public static final String KEY_DURATION="durationA";
	public static final String KEY_COMMENT_A="commentA";
	
	public static final String COLUMN_DESCRIPTION="description";
	
	public static final String NEW_ASD="newasd";
	public static final String NEW_AED="newaed";
	
	public static final String ASD="asd";
	public static final String AED="aed";
	public static final String PSD="psd";
	public static final String PED="ped";
	
}
