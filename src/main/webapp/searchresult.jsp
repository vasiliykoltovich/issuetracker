<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>DashBoard</title>
<link rel="icon" href="./img/it.ico" type="image/ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
</head>
<body>
	<c:import url="/header.jsp" />
<br>
<hr>
			<c:if test="${not empty projects}">
			<div class="row-fluid">
		<div class="container">
			
			<form name="projects" id="projectsTable" class="form-horizontal"
				action="./ProjectModify" method="post">
				<input type=hidden name="project" value="">
				<table class="table table-bordered">
					<caption class="lead">Projects with name <strong class="text-info">${searchword}</strong> </caption>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pr" items="${projects}">
							<tr>
								<td>${pr.id}</td>
								<td><a href="#" onClick="chooseProject(${pr.id})">${pr.name}</a></td>
								<td>${pr.description}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				</div>
			</div>
			</c:if>
		
			
	
		<c:if test="${not empty assignissues}">	
		<div class="row-fluid">
		<div class="container">
		
			<form name="issues" id="issuesTable" class="form-horizontal"
				action="./IssueController" method="post">
				<input type=hidden name="issue" value=""> 
				<input type=hidden name="sortBy" value=""> 
				<input type=hidden name="order" value="">
				<table class="table table-bordered">
					<caption class="lead">Issues for assignee with first name, last name or login is <strong class="text-info">${searchword}</strong></caption>
					<thead>
						<tr>
							<th>ID</th>
							<th>Assignee</th>
							<th>Project</th>
							<th>Description</th>
							<th>PSD</th>
							<th>PED</th>
							<th>ASD</th>
							<th>AED</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="issue" items="${assignissues}">
							
							<tr>

								<td><a href="#"
									onClick="chooseIssue(${issue.id},'./IssueController')">${issue.id}</a>
								</td>

                                <td>${issue.assignee.firstName} ${issue.assignee.lastName} as ${issue.assignee.login}</td>
								<td>${issue.project.name}</td>
								<td>${issue.description}</td>
								<td>${issue.psd}</td>
								<td>${issue.ped}</td>
								<td>${issue.asd}</td>
								<td>${issue.aed}</td>

								<td><c:choose>
										<c:when test="${issue.status.id == 1}">
											<span class="label label-success">${issue.status}</span>
										</c:when>
										<c:when test="${issue.status.id == 2}">
											<span class="label label-info">${issue.status}</span>
										</c:when>
										<c:when test="${issue.status.id == 3}">
											<span class="label label-primary">${issue.status}</span>
										</c:when>
										<c:when test="${issue.status.id == 4}">
											<span class="label label-default">${issue.status}</span>
										</c:when>
										<c:otherwise>
											<span class="label">${issue.status}</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			</div>
			</div>
			</c:if>
			
		
			<c:if test="${not empty statusIssues}">	
			<div class="row-fluid">
		<div class="container">
			
			<form name="issues" id="issuesTable" class="form-horizontal"
				action="./IssueController" method="post">
				<input type=hidden name="issue" value=""> 
				<input type=hidden name="sortBy" value=""> 
				<input type=hidden name="order" value="">
				<table class="table table-bordered">
					<caption class="lead">Issues for status  <strong class="text-info">${searchword}</strong></caption>
					<thead>
						<tr>
							<th>ID</th>
							<th>Project</th>
							<th>Description</th>
							<th>PSD</th>
							<th>PED</th>
							<th>ASD</th>
							<th>AED</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="issue" items="${statusIssues}">
							
							<tr>

								<td><a href="#"
									onClick="chooseIssue(${issue.id},'./IssueController')">${issue.id}</a>
								</td>

								<td>${issue.project.name}</td>
								<td>${issue.description}</td>
								<td>${issue.psd}</td>
								<td>${issue.ped}</td>
								<td>${issue.asd}</td>
								<td>${issue.aed}</td>

								<td><c:choose>
										<c:when test="${issue.status.id == 1}">
											<span class="label label-success">${issue.status}</span>
										</c:when>
										<c:when test="${issue.status.id == 2}">
											<span class="label label-info">${issue.status}</span>
										</c:when>
										<c:when test="${issue.status.id == 3}">
											<span class="label label-primary">${issue.status}</span>
										</c:when>
										<c:when test="${issue.status.id == 4}">
											<span class="label label-default">${issue.status}</span>
										</c:when>
										<c:otherwise>
											<span class="label">${issue.status}</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			</div>
			</div>
			</c:if>
	
	<c:if test="${ empty statusIssues}">
	<c:if test="${ empty assignissues}">
	<c:if test="${ empty projects}">
	<strong>Nothing found</strong>
	</c:if>	
	</c:if>
	</c:if>.
	
	
	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>