<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login Page</title>

<meta http-equiv="Content-Type" content="text/html" charset="UTF8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" >
<link rel="icon" href="./img/it.ico" type="image/ico" />
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" />
<link rel="stylesheet" href="./css/style.css" />

</head>
<body>
<div class="row-fluid">
    <div class="container">
          <div id="user-message" class="alert alert-error" ${not empty messageL ? '' : 'style="display:none"'}>
            <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>        
             
           
             <strong>
            <c:choose>
            <c:when test="${messageL == 'login'}">   
                  <strong>Wrong login</strong>
            </c:when>
            <c:when test="${messageL == 'password'}">   
                  <strong>Wrong password</strong>
            </c:when>
            </c:choose> 
            </strong>
          </div>
    </div></div>

	<div class="wrapper">
	
		<form id="loginForm" class="formsignin" name="form-login" method="post"
			action="./LogInController">
			<h2 class="formsigninheading">Please login</h2>
			<input type="text" class="form-control" id="login" name="login"
				placeholder="Login" required value="" /> 
				<input type="password"
				class="form-control" id="password" name="password"
				placeholder="Password" required value="" />

			<button class="btn btn-lg btn-primary btn-block" type="submit" onClick="checkLoginForm()">Enter</button>
		</form>
	</div>




</body>
</html>