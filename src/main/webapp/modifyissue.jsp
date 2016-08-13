<!DOCTYPE html>
<%@ page import="com.epam.issuetracker.Web_Constants"%>
<%@ page import="com.epam.issuetracker.DAO.DAOConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Modify Issue</title>
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

		<div class="page-header">
			<p class="lead">Please, modify the selected issue</p>
		</div>

		<div class="row">
			<form name="editIssue" id="editIssue" class="form-vertical"
				action="./IssueController" method="post">
				<input type="hidden" name="issue" value="${issue.id}" /> 
				<input
					type="hidden" id="commentD" name="comment" value="" />

				<div class="span4">
					<fieldset>
						<div class="control-group">
							<label id="descriptionL" class="control-label" for="description">Description</label>

							<div class="controls">
								<textarea class="input-xlarge span4" id="description"
									name="description" rows="6">
									${issue.description}</textarea>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<button type="button" id="submitButton"
									class="btn btn-large btn-info" onClick="checkEditIssue();">

									Edit Issue <i class="icon-ok icon-white"></i>
								</button>
							</div>
						</div>
					</fieldset>
				</div>

				<div class="span3">
					<fieldset>
						<c:choose>
							<c:when test="${issue.status.id == 1 }">
								<c:set var="statusStart" value="${issue.status.id-1}" />
								<c:set var="statusEnd" value="${issue.status.id + 4}" />
							</c:when>
							<c:when test="${issue.status.id == 2}">
								<c:set var="statusStart" value="${issue.status.id-2}" />
								<c:set var="statusEnd" value="${issue.status.id + 3}" />
							</c:when>
							<c:when test="${issue.status.id == 3 }">
								<c:set var="statusStart" value="${issue.status.id-3}" />
								<c:set var="statusEnd" value="${issue.status.id + 2}" />
							</c:when>
							<c:when test="${issue.status.id == 4 }">
								<c:set var="statusStart" value="${issue.status.id-4}" />
								<c:set var="statusEnd" value="${issue.status.id +1}" />
							</c:when>
						</c:choose>

						<div class="control-group">
							<label id="statusL" class="control-label" for="selectedStatus">Set
								Status</label>
							<div class="controls">
								<select id="status" name="selectedStatus"
									onChange="">
									<option disabled selected>Select status</option>
									<c:choose>
										<c:when test="${issue.status.id != 5}">
											<c:forEach var="i" items="${statuses}" begin="${statusStart}"
												end="${statusEnd}">
												<option value="${i.key}"
													${issue.status.id == i.key ? 'selected' : ''}>
													${i.value.status}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" items="${statuses}">
												<c:choose>
													<c:when test="${i.key == 1 || i.key == 5}">
														<option value="${i.key}"
															${issue.status.id == i.key ? 'selected' : ''}>${i.value.status}</option>
													</c:when>
												</c:choose>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>

					
						<div class="control-group">

							<label id="assigneeL" class="control-label" for="assigneeI">Assigned
								Persons</label>
							<div class="controls">
								<select id="assigneeI" name="selectedAssignee"
									onChange="sendAssignee();" required>
									<option disabled selected>Select Assignee</option>
									<c:forEach items="${assignees}" var="a">
										<option value="${a.id}">${a.firstName}${a.lastName}
											(${a.role})</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
				
				<!-- Issue information -->
				<div class="span4">
					<br>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Issue Property</th>
								<th>Value</th>
							</tr>
						</thead>
						<tr>
							<th>Issue ID</th>
							<td class="text-info">${issue.id}</td>
						</tr>
						<tr>
							<th>Project</th>
							<td class="text-info">${issue.project.name}</td>
						</tr>
						<tr>
							<th>Project Description</th>
							<td class="text-info">${issue.project.description}</td>
						</tr>
						<tr>
							<td>Issue Description</td>
							<td class="text-info">${issue.description}</td>
						</tr>
						<tr>
							<td>Assignees</td>
							<td class="text-info"><c:choose>
									<c:when test="${not empty assignees}">
										<c:forEach var="assignee" items="${assignees}">
		                  ${assignee.firstName} ${assignee.lastName} as ${assignee.role} ; 
		                                </c:forEach>
									</c:when>
									<c:otherwise>
										<span class="text-warning">Not setted</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td>Planned start date</td>
							<td class="text-info">${issue.psd}</td>
						</tr>
						<tr>
							<td>Planned end date</td>
							<td class="text-info">${issue.ped}</td>
						</tr>
						<tr>
							<td>Actual start date</td>
							<td class="text-info"><c:choose>
									<c:when test="${not empty issue.asd }">
					              ${issue.asd}
					               </c:when>
									<c:otherwise>
										<span class="text-warning">Not setted</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td>Actual end date</td>
							<td class="text-info"><c:choose>
									<c:when test="${not empty issue.aed }">
					              ${issue.aed}
					                </c:when>
									<c:otherwise>
										<span class="text-warning">Not setted</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<th>Current Status</th>
							<td class="text-info"><c:out
									value="${statuses[issue.status.id].status}" /></td>
						</tr>
					</table>
					<br>
					<div class="control-group">
						<div class="controls">
							<a href="#" id="expX" class="btn btn-large btn-info"
								onClick="exportToXML();">Export to XML <i
								class="icon-ok icon-white"></i></a>
						</div>
					</div>
				</div>
			</form>
		</div>

		<!-- Attachments -->

		<div class="row-fluid">
			<div class="container">
				<hr>
				<p class="lead">Edit Attachments</p>

				<form name="downloadAttachmentForm" id="downloadAttachmentForm"
					class="form-vertical" action="./AttachmentDownloading"
					method="post">
					<input type="hidden" name="attachmentId" value="" /> <input
						type="hidden" name="attachmentName" value="" />
					<c:forEach var="attachment" items="${issue.attachments}">
						<div>
							<a href="#"
								onClick="downloadFile('${attachment.id}', '${attachment.filename}');"><i
								class="icon-file"></i> ${attachment.filename}
								${attachment.filesize}Kb </a>
						</div>
					</c:forEach>
				</form>

				<form name="attachmentForm" id="attachmentForm"
					class="form-vertical" action="./AttachmentUploading" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="issue" value="${issue.id}" />
                    <input type="text"  id="fileDescription" name="fileDescription" value=""
                  placeholder="short description 2">
                  
					<div class="control-group">
						<label id="attachmentL" class="control-label" for="attachment">Attached
							files</label>
						<div class="controls">
							<input type="file" name="attachment" id="attachment">
						</div>
					</div>

					<div class="control-group">
						<div class="controls">
							<a href="#" class="btn btn-large btn-info"
								onClick="attachFile();">Attach File <i
								class="icon-plus icon-white"></i></a>
						</div>
					</div>
				</form>
			</div>

			<!-- Activities -->
			<div class="row-fluid">
				<div class="container">
					<hr>
					<p class="lead">Activity</p>

					<c:forEach var="activity" items="${activities}">
						<pre class="prettyprint">
    <span class="text-info">${activity.employee.firstName} ${activity.employee.lastName}</span>
    <span class="pull-right text-success">${activity.actDate}</span>
    
    <strong>Duration : ${activity.duration}</strong>
    <strong>Comment : ${activity.comment}</strong>
    </pre>
					</c:forEach>

					<hr>
					<form name="activityForm" id="activityForm" class="form-vertical"
						action="./SubmitActivity" method="post">
						<input type="hidden" id="assigneeAct" name="assigneeAct" value="">

						<input type="hidden" name="issue" value="${issue.id}" />

						<div class="control-group">
							<label id="commentL" class="control-label" for="comment">Activity
								Comment</label>
							<div class="controls">
								<textarea class="input-xlarge span4" id="comment"
									placeholder="Add new comment to activity" name="commentA"
									rows="6"></textarea>
							</div>
						</div>

						<div class="control-group">
							<label id="durationL" class="control-label" for="duration">Set
								duration as XX days, YY hours or ZZ minutes</label>
							<div class="controls">
								<textarea class="input-xs span4" id="duration" name="durationA"
									rows="2" required>
									</textarea>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<a href="#" class="btn btn-large btn-info"
									onClick="checkActivity();">Add Activity to Stream <i
									class="icon-ok icon-white"></i></a>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<div class="row-fluid">
		<c:import url="/footer.jsp" />
	</div>
</body>
</html>