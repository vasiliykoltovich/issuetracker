<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Issues</title>
<link rel="icon" href="img/it.ico" type="image/ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
</head>
<body>
	<c:import url="/header.jsp" />


	<div class="row-fluid">
		<div class="container">

			<form name="issues" id="issuesTable" class="form-horizontal"
				action="./IssueController" method="post">
				<input type=hidden name="issue" value=""> 
				<input type=hidden name="sortBy" value=""> 
				<input type=hidden name="order" value="">
				<table class="table table-bordered">
					<caption class="lead">Issues</caption>
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
						<c:forEach var="issue" items="${issues}">
							
							<tr>

								<td><a href="#"
									onClick="chooseIssue(${issue.key},'./IssueController')">${issue.key}</a>
								</td>

								<td>${issue.value.project.name}</td>
								<td>${issue.value.description}</td>
								<td>${issue.value.psd}</td>
								<td>${issue.value.ped}</td>
								<td>${issue.value.asd}</td>
								<td>${issue.value.aed}</td>

								<td><c:choose>
										<c:when test="${issue.value.status.id == 1}">
											<span class="label label-success">${issue.value.status}</span>
										</c:when>
										<c:when test="${issue.value.status.id == 2}">
											<span class="label label-info">${issue.value.status}</span>
										</c:when>
										<c:when test="${issue.value.status.id == 3}">
											<span class="label label-primary">${issue.value.status}</span>
										</c:when>
										<c:when test="${issue.value.status.id == 4}">
											<span class="label label-default">${issue.value.status}</span>
										</c:when>
										<c:otherwise>
											<span class="label">${issue.value.status}</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>


	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>