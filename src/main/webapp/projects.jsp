<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Projects</title>
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
			<form name="projects" id="projectsTable" class="form-horizontal"
				action="./ProjectModify" method="post">
				<input type=hidden name="project" value="">
				<table class="table table-bordered">
					<caption class="lead">Projects</caption>
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
								<td>${pr.key}</td>
								<td><a href="#" onClick="chooseProject(${pr.key})">${pr.value.name}</a></td>
								<td>${pr.value.description}</td>
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