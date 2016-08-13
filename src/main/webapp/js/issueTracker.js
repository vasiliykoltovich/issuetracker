function checkLoginForm() {
	var filled = true;
	var form = document.getElementById("loginForm");
	var password = document.getElementById("password").value.replace(/\s+/, '');
	var login = document.getElementById("login").value.replace(/\s+/, '');

	if (login.length) {
		login.style.color = "black";

	} else {
		filled = false;
		login.style.color = "#b94a48";
	}
	if (password.length) {
		password.style.color = "black";

	} else {
		filled = false;
		password.style.color = "#b94a48";
	}

	if (filled) {
		form.submit();
	}

}

function checkSearch(){
	var filled = true;
	var form= document.getElementById("searchform");
	var word =document.getElementById("word").value.replace(/\s+/, '');;
	var wordL =document.getElementById("wordL");
	var select = document.querySelector('.searchBox:checked').value;
	
	if ( !word.length) {
		filled = false;
		wordL.style.color = "#b94a48";
	} else {
		wordL.style.color = "black";
	}
	
	if(select==""){
		filled = false;
	}
	
	if (filled) {
		form.submit();
	}

}


function exportToXML(){
	
	var editIssue = document.getElementById("editIssue");
	editIssue.action = './XMLController';
	editIssue.submit();
}

function sortIssues(sortBy, order) {
	var table = document.getElementById("issuesTable");
	table.collumn.value = sortBy;
	table.order.value = order;
	table.action = './';
	table.submit();
}

function hideElement(id) {
	document.getElementById(id).style.display = "none";
}

function checkAddingIssue() {
	var filled = true;
	var addIssue = document.getElementById("addIssue");

	var message = document.getElementById("user-message");

	var description = document.getElementById("description").value.replace(/\s+/, '');
	var descriptionL = document.getElementById("descriptionL");

	var psdL = document.getElementById("psdL");
	var psd = document.getElementById("psd");
	var pedL = document.getElementById("pedL");
	var ped = document.getElementById("ped");

	var status = document.getElementById("status");
	var statusL = document.getElementById("statusL");

	var assigneeL = document.getElementById("assigneeL");
	var assignee = document.getElementById("assignee");

	var project = document.getElementById("project");
	var projectL = document.getElementById("projectL");

	var roleL = document.getElementById("roleL");
	var selectedRole = document.getElementById("selectedRole");

	if (description.length) {
		descriptionL.style.color = "black";
	} else {
		filled = false;
		descriptionL.style.color = "#b94a48";
	}

	if (status.options.selectedIndex == 0) {
		filled = false;
		statusL.style.color = "#b94a48";
	} else {
		statusL.style.color = "black";
	}
	if (assignee.options.selectedIndex == 0) {
		filled = false;
		assigneeL.style.color = "#b94a48";
	} else {
		assigneeL.style.color = "black";
	}
	if (selectedRole.options.selectedIndex == 0) {
		filled = false;
		roleL.style.color = "#b94a48";
	} else {
		roleL.style.color = "black";
	}
	if (project.options.selectedIndex == 0) {
		filled = false;
		projectL.style.color = "#b94a48";
	} else {
		projectL.style.color = "black";
	}

	if (filled) {
		addIssue.action = './SubmitIssue';
		addIssue.submit();
	} else {
		message.style.display = "block";
	}
}

function checkEditIssue() {
	var filled = true;
	var editIssue = document.getElementById("editIssue");
	var message = document.getElementById("user-message");
	
	var description = document.getElementById("description").value.replace(/\s+/, '');
	var assignee = document.getElementById("assigneeI");
	var status = document.getElementById("status");
	
	var statusL = document.getElementById("statusL");
	var descriptionL = document.getElementById("descriptionL");
	var assigneeL = document.getElementById("assigneeL");
	var exportL=document.getElementById("expX");
	
	if ( !description.length) {
		filled = false;
		descriptionL.style.color = "#b94a48";
	} else {
		descriptionL.style.color = "black";
	}
	if (assignee.options.selectedIndex == 0) {
		filled = false;
		assigneeL.style.color = "#b94a48";
	} else {
		assigneeL.style.color = "black";
	}
	if ( status.options.selectedIndex == 0) {
		filled = false;
		statusL.style.color = "#b94a48";
	} else {
		statusL.style.color = "black";
	}

	if (filled) {
		//exportL.disabled =false;
		editIssue.action = './ModifyIssue';
		editIssue.submit();
	} else {
		message.style.display = "block";
	}
}

function checkActivity() {
	var filled = true;
	
	var assignee = document.getElementById("assigneeI");
	var assigneeL = document.getElementById("assigneeL"); 
	var comment = document.getElementById("comment").value.replace(/\s+/, '');
	var commentL=document.getElementById("commentL");
	var duration = document.getElementById("duration").value.replace(/\s+/, '');
	var durationL=document.getElementById("durationL");
	
	if ( !comment.length) {
		filled = false;
		commentL.style.color = "#b94a48";
	} else {
		commentL.style.color = "black";
	}
	
	if ( !duration.length) {
		filled = false;
		durationL.style.color = "#b94a48";
	} else {
		durationL.style.color = "black";
	}
	
	if ( assignee.options.selectedIndex == 0) {
		filled = false;
		assigneeL.style.color = "#b94a48";
	} else {
		assigneeL.style.color = "black";
	}
	
	if (filled) {
		document.getElementById("activityForm").submit();
	} else {
		document.getElementById("user-message").style.display = "block";
	}
	
}

function attachFile() {
	if (document.getElementById("attachment").value) {
		document.getElementById("attachmentForm").submit();
	} else {
		document.getElementById("attachmentL").style.color = "#b94a48";
		document.getElementById("user-message").style.display = "block";
	}
}

function downloadFile(fileId, fileName) {
	var downloadForm = document.getElementById("downloadAttachmentForm");
	downloadForm.attachmentId.value = fileId;
	downloadForm.attachmentName.value = fileName;
	downloadForm.submit();
}

function submitNumber(number) {
	document.getElementById("issuesNumber").value = number;
	var endIndex=document.getElementById("endIndex");
	var startIndex=document.getElementById("startIndex");
	var page=document.getElementById("page");
	startIndex.value=0;
	endIndex.value=number-1;
	page.value=1;
	document.getElementById("number").submit();

}

function submitPage(multiplicator){
	var issuesNumber=document.getElementById("issuesNumber").value;
	var startIndex=document.getElementById("startIndex");
	var endIndex=document.getElementById("endIndex");
	var multiplicatorI=parseInt(multiplicator);
	startIndex.value=issuesNumber*multiplicatorI;
	var incM=multiplicatorI+1;
	endIndex.value=(issuesNumber*(incM)-1);
	var page=document.getElementById("page");
	
	
	if(multiplicatorI==0){
		page.value=1;
		
	}
	if(multiplicatorI==1){
		page.value=2;
		
	}
	if(multiplicatorI==2){
		page.value=3;
		
	}
	if(multiplicatorI==3){
		page.value=4;
		d
	}
	
	if(multiplicatorI==4){
		page.value=5;
		
	}
	
	
	
	
	document.getElementById("number").submit();
	
	
}


function userDataCheck() {
	var passed = true;
	
	var firstName = document.getElementById("firstName").value.replace(/\s+/,'');
	var firstNameL = document.getElementById("firstNameL");
	
	var lastName = document.getElementById("lastName").value.replace(/\s+/, '');
	var lastNameL = document.getElementById("lastNameL");
	
	var login = document.getElementById("login").value.replace(/\s+/, '');
	var loginL = document.getElementById("loginL");
	
	var password = document.getElementById("password").value.replace(/\s+/, '');
	var passwordL = document.getElementById("passwordL");
	
	var position = document.getElementById("position");
	var positionL = document.getElementById("positionL");
	
	var message = document.getElementById("user-message");
	
	if (!firstName.length) {
		passed = false;
		firstNameL.style.color = "#b94a48";
	} else {
		firstNameL.style.color = "black";
	}
	if (!lastName.length) {
		passed = false;
		lastNameL.style.color = "#b94a48";
	} else {
		lastNameL.style.color = "black";
	}
	if (!login.length) {
		passed = false;
		loginL.style.color = "#b94a48";
	} else {
		loginL.style.color = "black";
	}
	
	if (!password.length) {
		passed = false;
		passwordL.style.color = "#b94a48";
	} else {
		passwordL.style.color = "black";
	} 
	
	if (position.options.selectedIndex != 0) {
		positionL.style.color = "black";
	} else {
		passed = false;
		positionL.style.color = "#b94a48";
	}
	
	if (passed) {
		document.getElementById("userData").submit();
	} else {
		message.style.display = "block";
	}
}

function chooseIssue(issueId) {
	var table = document.getElementById("issuesTable");
	table.issue.value = issueId;
	table.action = './IssueController';
	table.submit();
}

function chooseProject(projectId) {
	var table = document.getElementById("projectsTable");
	table.project.value = projectId;
	table.submit();
}
function chooseUser(userId) {
	var table = document.getElementById("usersTable");
	table.user.value = userId;
	table.submit();
}
function checkModify(formId) {
	var name = document.getElementById("name").value.replace(/\s+/, '');
	var label = document.getElementById("label");
	var message = document.getElementById("user-message");

	if (!name.length) {
		label.style.color = "#b94a48";
		message.style.display = "block";
	} else {
		label.style.color = "black";
		document.getElementById(formId).submit();
	}
}
function addProject() {

	var passed = true;
	var name = document.getElementById("name").value.replace(/\s+/, '');
	var nameL = document.getElementById("nameL");

	var description = document.getElementById("description").value.replace(
			/\s+/, '');
	var descriptionL = document.getElementById("descriptionL");
	var message = document.getElementById("user-message");

	if (!name.length) {
		passed = false;
		nameL.style.color = "#b94a48";
	} else {
		nameL.style.color = "black";
	}

	if (!description.length) {
		passed = false;
		descriptionL.style.color = "#b94a48";
	} else {
		descriptionL.style.color = "black";
	}
	if (passed) {
		document.getElementById("projectAdd").submit();
	} else {
		message.style.display = "block";
	}
}
function editProject() {
	var passed = true;
	
	var name = document.getElementById("name").value.replace(/\s+/, '');
	var nameL = document.getElementById("nameL");
	
	var description = document.getElementById("description").value.replace(/\s+/, '');
	var descriptionL = document.getElementById("descriptionL");
	var message = document.getElementById("user-message");

	if (!name.length) {
		passed = false;
		nameL.style.color = "#b94a48";
	} else {
		nameL.style.color = "black";
	}
	
	if (!description.length) {
		passed = false;
		descriptionL.style.color = "#b94a48";
	} else {
		descriptionL.style.color = "black";
	}
	if (passed) {
		document.getElementById("projectModify").submit();
	} else {
		message.style.display = "block";
	}
}

function sendAssignee() {
	var selectedAss = document.getElementById("assigneeI").value;
	var ActForm = document.forms["activityForm"]["assigneeAct"].value;
	var hidden = document.getElementsByName("assigneeAct")[0];
	hidden.value = selectedAss;
}

function addUser() {
	var passed = true;
	
	var firstName = document.getElementById("firstName").value.replace(/\s+/,'');
	var firstNameL = document.getElementById("firstNameL");
	var lastName = document.getElementById("lastName").value.replace(/\s+/, '');
	var lastNameL = document.getElementById("lastNameL");
	var login = document.getElementById("login").value.replace(/\s+/, '');
	var loginL = document.getElementById("loginL");
	var password = document.getElementById("password").value.replace(/\s+/, '');
	var passwordL = document.getElementById("passwordL");
	var confirmation = document.getElementById("confirmation").value.replace(/\s+/, '');
	var confirmationL = document.getElementById("confirmationL");

	var position = document.getElementById("position");
	var positionL = document.getElementById("positionL");

	var message = document.getElementById("user-message");

	if (!firstName.length) {
		passed = false;
		firstNameL.style.color = "#b94a48";
	} else {
		firstNameL.style.color = "black";
	}
	if (!lastName.length) {
		passed = false;
		lastNameL.style.color = "#b94a48";
	} else {
		lastNameL.style.color = "black";
	}
	if (!login.length) {
		passed = false;
		loginL.style.color = "#b94a48";
	} else {
		loginL.style.color = "black";
	}
	if (position.options.selectedIndex != 0) {
		positionL.style.color = "black";
	} else {
		passed = false;
		positionL.style.color = "#b94a48";
	}
	if (!password.length) {
		passed = false;
		passwordL.style.color = "#b94a48";
	} else {
		passwordL.style.color = "black";
	}
	if (!confirmation.length) {
		passed = false;
		confirmationL.style.color = "#b94a48";
	} else {
		confirmationL.style.color = "black";
	}
	if (passed) {
		document.getElementById("UserAdd").submit();
	} else {
		message.style.display = "block";
	}
}

$('#product-table a:first').tab('show');

$(document).ready(function() {
	$('#multidemo').multiselect();
});

$(document).ready(function() {
	$('#multiple-selector').multiselect();
});
