<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <nav class="navbar navbar-inverse" role="navigation">
  <div class="container">
   <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<%= request.getContextPath() %>/home">SupSMS</a>
   </div>
    
   <div class="navbar-collapse collapse">
    <ul class="nav navbar-nav">
     <li><a href="<%= request.getContextPath() %>/offers">Offers</a></li>
     <c:if test="${sessionScope.user != null}">
	  <li><a href="<%= request.getContextPath() %>/auth/profile">My profile</a></li>
	  <li><a href="<%= request.getContextPath() %>/auth/newMessage">Send a message</a></li>
	  <li><a href="<%= request.getContextPath() %>/auth/contact">Manage your contacts</a></li>
	  <c:if test="${sessionScope.user.isAdmin}">
	   <li class="dropdown">
	    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Admin Panel <span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
         <li><a href="<%= request.getContextPath() %>/auth/admin/user">Manage user</a></li>
         <li><a href="<%= request.getContextPath() %>/auth/admin/invoice">Manage invoice</a></li>
        </ul>
        </li>
	  </c:if>
	 </c:if>
	</ul>
	<ul class="nav navbar-nav navbar-right">
	 <c:choose>
      <c:when test="${sessionScope.user != null}">
	   <li><a href="<%= request.getContextPath() %>/auth/logout">Logout</a></li>
	  </c:when>
	  <c:otherwise>
	   <li><a href="<%= request.getContextPath() %>/login">Log in</a></li>
	   <li><a href="<%= request.getContextPath() %>/register">Register</a></li>
	  </c:otherwise>
	 </c:choose>
	</ul>
   </div>
  </div>
 </nav>