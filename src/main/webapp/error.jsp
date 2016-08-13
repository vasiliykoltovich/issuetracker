<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Error</title>
<link rel="icon" href="img/it.ico" type="image/ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/issueTracker.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
</head>
<body>
<c:import url="/header.jsp"/>

	<div class="container">
       <div class="page-header">
          <p class="lead">Error</p>
       </div>   
       <div id="user-message" class="alert alert-error">
           <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>        
       <strong>Error Message</strong>
       <hr>
       <pre class="prettyprint">${error}</pre>
 
       <hr>
       </div>
    </div>    

<div class="row-fluid">
    <c:import url="/footer.jsp"/>
    </div> 
</body>
</html>