<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-static-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="brand" href="./dash.jsp">Issues Tracker </a>

			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="divider-vertical"></li>

					<li><a href="#" data-toggle="modal" data-target="#myModal">Search
							<i class="icon-search"></i>
					</a></li>

					<c:choose>
						<c:when test="${not empty user}">
							<c:choose>
								<c:when test="${user.position.id == 4 || user.position.id == 5}">
									<li><a href="./AddIssue">Create Issue <i
											class="icon-plus-sign"></i></a></li>
								</c:when>
							</c:choose>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><i class="icon-wrench"></i> DashBoard
									Menu <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="./IssuesController">Issues</a></li>
									<li><a href="./ProjectController">Projects</a></li>
								</ul></li>
						</c:when>
					</c:choose>
				</ul>

				<div class="nav pull-right">
					<c:choose>
						<c:when test="${not empty user}">
							<a class="btn btn-link" href="./userPage.jsp"><i
								class="icon-user"></i> ${user.firstName} ${user.lastName} </a>
							<a class="btn btn-info" href="./LogOutController">Log Out</a>
						</c:when>

					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>


<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<form role="form" class="form-inline" id="searchform" method="post" action="./SearchController">
					<div class="form-group">
						<label for="word" id="wordL" >Search word:</label> 
						<input type="text"
							class="form-control" id="word" name="searchword">
					</div>
					<div class="checkbox">
						<label><input type="checkbox" class="searchBox" name="selector" id="selector" value="1">project</label>
						 <label>
						<input type="checkbox" class="searchBox" name="selector" id="selector" value="2">status</label> 
						<label>
						<input type="checkbox" class="searchBox" name="selector" id="selector" value="3">assignee</label> 
						<label>
						<input type="checkbox" class="searchBox" name="selector" id="selector" value="4">all</label>
					</div>
					<button type="button" class="btn btn-default" onClick="checkSearch();">Search</button>

					<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
				</form>
			</div>
		</div>
	</div>
</div>
