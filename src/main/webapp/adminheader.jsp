<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="navbar navbar-static-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="./dash.jsp">Issue Tracker</a>
          
        <div class="nav-collapse collapse">
            <ul class="nav">
          <li class="divider-vertical">
          </li>   
                   
              
              <c:choose>
                 <c:when test="${not empty user}">
                 
                 <c:choose>
                       <c:when test="${user.position.id == 6 }">
                 <li>
                 <a href="./AddProject">Create Project <i class="icon-plus-sign"></i></a>
                 <a href="./AddEmployee">Create Employee <i class="icon-plus-sign"></i></a>
                 </li>
                 </c:when>
                    </c:choose>
                 
                    </c:when>
              </c:choose>  
                              
            </ul>
            
            <div class="nav pull-right">                      
              <c:choose>
                <c:when test="${not empty user}">
                   <a class="btn btn-link" href="./" disabled><i class="icon-user"></i>
                    ${user.firstName} ${user.lastName} ${user.position} </a>
                          
                   <a class="btn btn-info" href="./LogOutController">LogOut</a>
                </c:when>
                <c:otherwise>          
                   <a href="#myModal" role="button" class="btn btn-info" data-toggle="modal"></a>
                </c:otherwise>
              </c:choose>
             </div>         
            </div>
           </div>                 
          </div>                        
        </div>
       