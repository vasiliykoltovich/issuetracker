<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Add Issue</title>
<link rel="icon" href="img/it.ico" type="image/ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
</head>
<body>
	<c:import url="/header.jsp" />

	<div class="container">
		<div id="user-message" class="alert alert-error" style="display: none">
			<button type="button" class="close"
				onClick="hideElement('user-message');">
				<i class="icon-off"></i>
			</button>
			<strong>Fields are not filled</strong>
		</div>

		<div class="page-header">
			<p class="lead">New Issue Configuration</p>
		</div>

		<div class="row">

			<form name="addIssue" id="addIssue" class="form-vertical"
				action="./AddIssue" method="post">

				<div class="span5">
					<fieldset>

						<div class="control-group">
							<label id="descriptionL" class="control-label" for="description">Add
								Description</label>
							<div class="controls">
								<textarea class="input-xlarge span4" id="description"
									name="description" rows="11"
									placeholder="Issue description here" required></textarea>
							</div>
						</div>

						<div class="control-group">
							<label id="psdL" class="control-label" for="psd">Planned
								Start Date</label>
							<div class="controls">
								<input id="psd" name="psd" class="date" type="date" value="" required>
							</div>
						</div>

						<div class="control-group">
							<label id="pedL" class="control-label" for="ped">Planned
								End Date</label>
							<div class="controls">
								<input id="ped" name="ped" class="date" type="date" value="" required>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">

								<button type="button" id="submitButton"
									class="btn btn-large btn-info" onClick="checkAddingIssue();">
									Add New Issue <i class="icon-ok icon-white"></i>
								</button>
								
								<input type="reset" id="clearButton"
									class="btn btn-large btn-info" value="Cancel" >
									<i class="icon-ok icon-white"></i>
							</div>
						</div>

					</fieldset>
				</div>
				<div class="span3">
					<fieldset>

						<div class="control-group">
							<label id="statusL" class="control-label" for="selectedStatus">Select
								status</label>
							<div class="controls">
								<select id="status" name="selectedStatus"
									onChange="" required>
									<option disabled selected>Selected status</option>
									<c:forEach var="i" items="${statuses}" begin="0" end="1">
										<option value="${i.key}"
											>${i.value.status}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="control-group">
							<label id="projectL" class="control-label" for="selectedProject">Select
								Project</label>
							<div class="controls">
								<select id="project" name="selectedProject" 
									required>
									<option disabled selected>Selected project</option>
									<c:forEach var="i" items="${projects}">
										<option value="${i.key}"
											>${i.value.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="control-group">
						
							<label id="assigneeL" class="control-label" for="selectedAssignee">Select
								assignee</label>
							<div class="controls">
								<select id="assignee" name="selectedAssignee" required multiple >
									<option disabled selected>Selected assignee</option>
									<c:forEach items="${users}" var="i">
										<option value="${i.id}"  onselect="openRole()" 
											>${i.firstName}
											${i.lastName} (${i.position})
											</option>	
									</c:forEach>
								</select>
								
							</div>
							
						</div>


						<div class="control-group">
							<label id="roleL" class="control-label" for="selectedRole">Select
								role for assignees in same order</label>
							<div class="controls">
								<select id="selectedRole" name="selectedRole" required multiple>
									<option disabled selected>Selected role</option>
									<c:forEach items="${roles}" var="i">
										<option value="${i.key}"
											>${i.value.role}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</fieldset>
				</div>
			</form>
		</div>
	</div>


	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>