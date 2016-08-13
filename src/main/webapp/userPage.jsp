<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>User Page</title>
<link rel="icon" href="img/it.ico" type="image/ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
</head>
<body>
<c:choose>
<c:when test="${user.position.id!=7}">
     <c:import url="/header.jsp" />
     </c:when>
     <c:otherwise>
     <c:import url="/adminheader.jsp" />
     </c:otherwise>
     </c:choose>
	

	<div class="row-fluid">
		<div class="container">
			<div id="user-message" class="alert alert-error"
				${not empty message ? '' : 'style="display:none"'}>
				<button type="button" class="close"
					onClick="hideElement('user-message');">
					<i class="icon-off"></i>
				</button>
				<strong>Fields are not filled</strong>
			</div>


			<div class="page-header">
				<p class="lead">User Data</p>
			</div>
		</div>
	</div>


	<div class="row-fluid" style="height: 400px">
		<div class="container">



			<div class="span10">
				<form class="form-horizontal" name="userData" id="userData"
					action="./SubmitEmployee" method="post">
					<input type=hidden name="employeeId" value="${employee.id}">
					<div class="control-group">
						<label class="control-label" id="firstNameL" for="firstName">First
							Name</label>
						<div class="controls">
							<input type="text" id="firstName" name="firstName" ${user.position.id==7?'':'disabled'}
								value="${user.position.id==7 ? employee.firstName : user.firstName}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" id="lastNameL" for="lastName">Last
							Name</label>
						<div class="controls">
							<input type="text" id="lastName" name="lastName" ${user.position.id==7?'':'disabled'}
								value="${user.position.id==7 ? employee.lastName : user.lastName}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" id="loginL" for="login">Login</label>
						<div class="controls">
							<input type="text" id="login" name="login"  ${user.position.id==7?'':'disabled'}
								value="${user.position.id==7 ? employee.login : user.login}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" id="passwordL" for="password">Password</label>
						<div class="controls">
							<input type="password" id="password" name="password" ${user.position.id==7?'':'disabled'}
								value="${user.position.id==7 ? employee.password : user.password}">
						</div>
					</div>
                     <div class="control-group">
						<label class="control-label" id="cpositionL" for="cposition">Current Position</label>
						<div class="controls">
							<input type="text" id="cposition" name="cposition" disabled
								value="${user.position.id==7 ? employee.position : user.position}">
						</div>
					</div>

					<div class="control-group">
						<label id="positionL" class="control-label" for="position">New
							Position</label>
						<div class="controls">
							<select id="position" name="position"  ${user.position.id==7?'':'disabled'} >
								<option disabled selected>Select employee position</option>
								<option value="1">Junior</option>
								<option value="2">Software developer</option>
								<option value="3">Senior</option>
								<option value="4">Teamlead</option>
								<option value="5">Project manager</option>
								<option value="6">QA-engineer</option>

							</select>
						</div>
					</div>
					<br>
					<div class="control-group">
						<div class="controls">
							<button type="button" class="btn btn-info"
								onClick="userDataCheck();" ${user.position.id==7 ? '' : 'disabled'}>
								Edit with new values <i class="icon-edit icon-white"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>





	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>