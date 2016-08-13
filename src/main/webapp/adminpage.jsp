<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Administrator Page</title>


<link rel="icon" href="img/it.ico" type="image/ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css">
</head>
<body>
<c:import url="\adminheader.jsp"/> 

<br>
<div class="row-fluid">
    <div class="container">
    <form name="users" id="usersTable" class="form-horizontal" action="./EmployeeController" method="post">    
    <input type=hidden name="user" value="">
    <table class="table table-bordered">
		<caption class="lead">Employee List</caption>
		<thead>
		<tr>
		<th>ID</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Login</th>
		<th>Position</th>
		</tr>
		</thead>
		<tbody>		
		<c:forEach var="user" items="${users}">
		<tr>
		<td><a href="#" onClick="chooseUser(${user.key})">${user.key}</a></td>
		<td>${user.value.firstName}</td>
		<td>${user.value.lastName}</td>
		<td>${user.value.login}</td>
		<td>${user.value.position}</td>
		</tr>
		</c:forEach>
		</tbody>
     </table>    
    </form>          
    </div>
    </div>
    
     
    <div class="row-fluid">
    <div class="container">
        <a href="./addEmployee.jsp" class="btn btn-primary btn-info">Add New Employee <i class="icon-plus icon-white"></i> </a>
    </div></div>
      

<div class="row-fluid">
    <div class="container">
    <form name="projects" id="projectsTable" class="form-horizontal" action="./ProjectModify" method="post">    
    <input type=hidden name="project" value="">
    <table class="table table-bordered">
		<caption class="lead">Projects List</caption>
		<thead>
		<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Description</th>
		</tr>
		</thead>
		<tbody>		
		<c:forEach var="project" items="${projects}">
		<tr>
		<td>${project.key}</td>
		<td><a href="#" onClick="chooseProject(${project.key})">${project.value.name}</a></td>
		<td>${project.value.description}</td>
		</tr>
		</c:forEach>
		</tbody>
     </table>    
    </form>          
    </div>
    </div>


<div class="row-fluid">
    <div class="container">
        <a href="./addProject.jsp" class="btn btn-primary btn-info">Add New Project <i class="icon-plus icon-white"></i> </a>
    </div></div>
 

<div class="row-fluid">
    <c:import url="\footer.jsp"/>
    </div> 
</body>
</html>