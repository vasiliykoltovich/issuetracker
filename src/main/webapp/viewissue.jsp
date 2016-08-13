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
			<table class="table table-bordered table-hover">
				<caption class="lead">Chosen issue</caption>
				<thead>
					<tr>
						<th style="width: 25%">Issue Property</th>
						<th style="width: 75%">Value</th>
					</tr>
				</thead>
				<tr>
					<td><span class="text-info">ID</span></td>
					<td>${issue.id}</td>
				</tr>

				<tr>
					<td><span class="text-info">Project</span></td>
					<td>${issue.project.name}</td>
				</tr>

				<tr>
					<td><span class="text-info">Assignees</span></td>
					<td><c:choose>
							<c:when test="${not empty assignees}">
								<c:forEach var="assignee" items="${assignees}">
		    ${assignee.firstName} ${assignee.lastName} ; 
		    </c:forEach>
							</c:when>
							<c:otherwise>
								<span class="text-warning">not setted</span>
							</c:otherwise>
						</c:choose></td>
				</tr>

				<tr>
					<td><span class="text-info">Description</span></td>
					<td>${issue.description}</td>
				</tr>

				<tr>
					<td><span class="text-info">Planned start date</span></td>
					<td>${issue.psd}</td>
				</tr>

				<tr>
					<td><span class="text-info">Planned end date</span></td>
					<td>${issue.ped}</td>
				</tr>

				<tr>
					<td><span class="text-info">Actual start date</span></td>
					<td>${issue.asd}</td>
				</tr>
				<tr>
					<td><span class="text-info">Actual end date</span></td>
					<td>${issue.aed}</td>
				</tr>

				<tr>
					<td><span class="text-info">Status</span></td>
					<td>${issue.status}</td>
				</tr>

			</table>
		</div>
	</div>

	<hr>

	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>