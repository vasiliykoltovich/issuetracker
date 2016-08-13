<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Add Project</title>
<link rel="icon" href="img/it.ico" type="image/ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/issueTracker.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
</head>
<body>
<c:import url="\adminheader.jsp"/> 

<div class="row-fluid">
    <div class="container">
          <div id="user-message" class="alert alert-error" ${not empty message ? '' : 'style="display:none"'}>
            <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>        
            <strong>${message}</strong>
          </div>
    </div></div>



<div class="row-fluid" style="height:600px">
    <div class="container">
    <p class="lead">Add New Project</p>
    <hr>
    <form name="projectAdd" id="projectAdd" class="form-horizontal" action="./AddProject" method="post">    
          <div class="control-group">
          <label class="control-label" for="name" id="nameL">Project Name</label>
             <div class="controls">
                 <input type="text" name="name" id="name">
          </div></div>

 
          <div class="control-group">
          <label class="control-label" for="description" id="descriptionL">Description</label>
             <div class="controls">
                 <textarea class="input-xlarge" rows="4" name="description" id="description"></textarea>
          </div></div>

               <br>
          <div class="control-group">
              <div class="controls">
          <button type="button" class="btn btn-primary btn-info" onClick="addProject();">Add Project+<i class="icon-edit icon-white"></i> </button>
          </div></div>
    </form>          
    </div></div>
    


<div class="row-fluid">
    <c:import url="\footer.jsp"/>
    </div> 
</body>
</html>