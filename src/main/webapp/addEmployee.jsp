<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Add Employee</title>
<link rel="icon" href="img/it.ico" type="image/ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/issueTracker.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
</head>
<body>
<c:import url="./adminheader.jsp"/> 

<div class="row-fluid">
    <div class="container">
          <div id="user-message" class="alert alert-error" ${not empty message ? '' : 'style="display:none"'}>
            <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>        
            <strong>${message}</strong>
           
          </div>
    </div></div>

<div class="row-fluid" style="height:600px">
    <div class="container">
        <div class="page-header">
          <p class="lead">Add New Employee</p>
        </div>
        
        <div class="span10"> 
             <form class="form-horizontal"  name="UserAdd" id="UserAdd" action="./AddEmployee" method="post">    
                       
                <div class="control-group">
                  <label class="control-label" id="firstNameL" for="firstName">First Name</label>
                <div class="controls">
                  <input type="text" id="firstName" name="firstName" value="">
                </div></div>
                
                <div class="control-group">
                  <label class="control-label" id="lastNameL" for="lastName">Last Name</label>
                <div class="controls">
                  <input type="text"  id="lastName" name="lastName" value="">
                </div></div>
                
                <div class="control-group">
                  <label class="control-label" id="loginL" for="login">Login</label>
                <div class="controls">
                  <input type="text" id="login" name="login" value="">
                </div></div>
                
                <div class="control-group">
                  <label class="control-label" id="passwordL" for="password">Password</label>
                <div class="controls">
                  <input type="password" id="password" name="password" value="">
                </div></div>
                
                <div class="control-group">
                  <label class="control-label" id="confirmationL" for="confirmation">Repeat Password</label>
                <div class="controls">
                  <input type="password"  id="confirmation" name="confirmation" value="">
                </div></div>
                
                
                
                <div class="control-group">
                    <label id="positionL" class="control-label" for="position">Position</label>
                <div class="controls">   
                    <select id="position" name="position">
                    <option disabled selected>Select employee position</option>
                    <option value="1">Junior</option>
                    <option value="2">Software developer</option>
                     <option value="3">Senior</option>
                      <option value="4">Teamlead</option>
                       <option value="5">Project manager</option>
                        <option value="6">QA-engineer</option>
                    
                    </select>       
                </div></div>
                <br>
                <div class="control-group">
                <div class="controls">
                <button type="button" class="btn btn-info" onClick="addUser();">Add New User <i class="icon-edit icon-white"></i> </button>
                </div></div>
             </form>           
        </div>
    </div>
    </div>



<div class="row-fluid">
    <c:import url="\footer.jsp"/>
    </div> 
</body>
</html>