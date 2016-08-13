<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Project Page</title>
<link rel="icon" href="img/it.ico" type="image/ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css"/>
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
          <div id="user-message" class="alert alert-error" ${not empty message ? '' : 'style="display:none"'}>
            <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>        
            <strong>Fields are not filled</strong>
          </div> 
    </div>
    </div>

    <div class="row-fluid" style="height:400px">
    <div class="container">
    <p class="lead">Project Modify</p>
    <hr>
    <form name="projectModify" id="projectModify" class="form-horizontal" action="./SubmitProject" method="post">    
          <input type=hidden name="projectId" value="${chproject.id}">

          <div class="control-group">
          <label class="control-label" for="name" id="nameL">Project Name</label>
             <div class="controls">
                 <input type="text" name="name" id="name" value="${chproject.name}" ${user.position.id==7?'':'disabled'}>
          </div>
          </div>

          <div class="control-group">
          <label class="control-label" for="description" id="descriptionL">Description</label>
             <div class="controls">
                 <textarea class="input-xlarge" rows="4" name="description" id="description" 
                 placeholder="${chproject.description}"  ${user.position.id==7?'':'disabled'}></textarea>
          </div></div>

          <div class="control-group">
              <div class="controls">
          <button type="button" class="btn btn-primary btn-info" onClick="editProject();"
        	  ${user.position.id==7 ? '' : 'disabled'}
          >Modify Project <i class="icon-edit icon-white"></i> </button>
          </div>
          </div>
    </form>
    </div>
    </div>
    
    <hr>

<div class="row-fluid">
    <c:import url="/footer.jsp"/>
    </div> 
</body>
