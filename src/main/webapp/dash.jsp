<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>DashBoard</title>
<link rel="icon" href="./img/it.ico" type="image/ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/issueTracker.js"></script>
<link rel="stylesheet" href="./css/bootstrap.css" type="text/css" />
</head>
<body>
	<c:import url="/header.jsp" />


	<ul class="nav nav-tabs" id="product-table">
		<li class="active">
		<a data-toggle="tab" href="#table1">Activity
				Stream...</a></li>
		<li>
		<a data-toggle="tab" href="#table2">Assigned to me..</a></li>
	</ul>

	<div class="tab-content">
		<div id="table1" class="tab-pane active">
		
		
		<div class="row-fluid">
				<div class="container">
					
					<c:forEach var="activity" items="${activities}" 
					
					begin="${not empty startIndex ? (startIndex) : 0}" 
					
					end="${not empty issuesNumber ? endIndex : (number-1)}">
						<pre class="prettyprint">
    <span class="text-info">${activity.employee.firstName} ${activity.employee.lastName}</span>
    <span class="pull-right text-info">${activity.actDate}</span>
    
    <strong>Project</strong> : ${activity.project.name}
    <strong>Duration</strong> : ${activity.duration}
    <strong>Comment</strong> : ${activity.comment}
    </pre>
					</c:forEach>
					</div>
					</div>
	
        <div class="row-fluid">
				<div class="container">
					<div class="span6">
						<form class="form-inline" name="numberOfIssues" id="number"
							action="./DummyController" method="post">
							<input type=hidden id="issuesNumber" name="issuesNumber"
							 value="${not empty issuesNumber ? issuesNumber : 5}">
							<input type=hidden id="startIndex" name="startIndex" value="0">
							<input type=hidden id="endIndex" name="endIndex" 
							
							
							value="4">
							<input type=hidden id="page" name="page" value="${not empty page? page : '1'}" >
							<div class="pagination pagination-small">
								
								<ul>
									<li><button type="button" class="btn btn-primary btn-xs" onClick="submitNumber('5')"
										${not empty issuesNumber ? issuesNumber == 5 ? 'disabled' : '' : 'disabled'}>5</button></li>
									<li><button type="button" class="btn btn-primary btn-xs" onClick="submitNumber('10')"
										${issuesNumber == 10 ? 'disabled' : ''}>10</button></li>
									<li><button type="button" class="btn btn-primary btn-xs" onClick="submitNumber('15')"
										${issuesNumber == 15 ? 'disabled' : ''}>15</button></li>
									<li><button type="button" class="btn btn-primary btn-xs" onClick="submitNumber('20')"
										${issuesNumber == 20 ? 'disabled' : ''}>20</button></li>
								</ul>
							</div>
						</form>
					</div>
<!-- comment 	-->				
<div class="pull-right">
<span class="label label-info label-md" >Page<span class="badge badge-md"> ${page} </span></span>
</div>


					<div class="pagination pagination-small pagination-right span6">
						<ul >
							<li id="p0" class=""  >
							<button type="button" class="btn btn-primary btn-xs"  onclick="submitPage('0')"  >1</button></li>
							<li id="p1" class="">
							<button type="button" class="btn btn-primary btn-xs"  onclick="submitPage('1')">2</button></li>
							<li id="p2" class="">
							<button type="button" class="btn btn-primary btn-xs"  onclick="submitPage('2')">3</button></li>
							<li id="p3" class="">
							<button type="button"  class="btn btn-primary btn-xs"  onclick="submitPage('3')">4</button></li>
							<li id="p4" class="">
							<button type="button"  class="btn btn-primary btn-xs"  onclick="submitPage('4')">5</button></li>
							
						</ul>
					</div>
					
				</div>
			</div>
			
		</div><!-- end class tab pane -->


		<!-- panel 2 -->

		<div id="table2" role="tabpanel" class="tab-pane">
			
			<div class="row-fluid">
				<div class="container">
				
					<form name="tasks" id="issuesTable" class="form-horizontal" action=""
						method="post">
						<input type=hidden name="issue" value=""> <input
							type=hidden name="collumn" value=""> <input type=hidden
							name="order" value="">

						<table class="table table-bordered">
							<caption class="lead">Assigned to me</caption>
							<thead>
								<tr>
							<th>ID</th>
							<th>Project</th>
							<th>Description</th>
							<th>PSD</th>
							<th>PED</th>
							<th>ASD</th>
							<th>AED</th>
							<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="issue" items="${issues}" begin="0"
									end="${not empty issuesNumber ? (issuesNumber-1) : (number-1)}">
									<tr>
										<td><a href="#" onClick="chooseIssue(${issue.id})">${issue.id}</a>
										</td>
										<td>${issue.project.name}</td>
										<td>${issue.description}</td>
                                        <td>${issue.psd}</td>
								        <td>${issue.ped}</td>
								        <td>${issue.asd}</td>
								        <td>${issue.aed}</td>
										<td><c:choose>
												<c:when test="${issue.status.id == 1}">
													<span class="label label-success">${issue.status}</span>
												</c:when>
												<c:when test="${issue.status.id == 2}">
													<span class="label label-info">${issue.status}</span>
												</c:when>
												<c:when test="${issue.status.id == 3}">
													<span class="label label-primary">${issue.status}</span>
												</c:when>
												<c:when test="${issue.status.id == 4}">
													<span class="label label-default">${issue.status}</span>
												</c:when>
												<c:otherwise>
													<span class="label">${issue.status}</span>
												</c:otherwise>
											</c:choose></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<!-- end row fluid -->
			
		</div>
	</div>
	
	
			
	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>