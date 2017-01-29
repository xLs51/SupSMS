<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<%@ include file="../template/header.jsp" %>
	</head>
	<body>
		<%@ include file="../template/navbar.jsp" %>
				
		<div class="container">
			<%@ include file="../template/infoViewer.jsp" %>
			<div class="page-header">
				<h1>Profile</h1>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Account Informations</h3>
		    			</div>
		    			<div class="panel-body">
				            <label>Username : </label>
			    			<c:out value="${requestScope.user.username}"></c:out>
			    			<br>
			    			<label>Firstname : </label>
			    			<c:out value="${requestScope.user.firstName}"></c:out>
			    			<br>
			    			<label>Lastname : </label>
			    			<c:out value="${requestScope.user.lastName}"></c:out>
			    			<br>
			    			<label>Phone number : </label>
			    			<c:out value="${requestScope.user.phone}"></c:out>
			    			<br>
			    			<label>E-mail : </label>
		   					<c:out value="${requestScope.user.mail}"></c:out>
							<br>
						</div>
		            </div>
            	</div>
            	
            	<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								My current invoice
							</h3>
		    			</div>
			   			<div class="panel-body">
			   				<label>Name : </label>
			   				<c:out value="${requestScope.user.invoice.name}"></c:out>
			   				<br>
			   				<label>Price : </label>
							<c:out value="${requestScope.user.invoice.price}"></c:out>
							<br>
							<label>Number of SMS : </label>
							<c:choose>
		   						<c:when test="${requestScope.user.invoice.nbOfSMS < 0}">
		   							<c:out value="Unlimited"></c:out>
		   						</c:when>
		   						<c:otherwise>
		   							<c:out value="${requestScope.user.invoice.nbOfSMS}"></c:out> SMS
		   						</c:otherwise>
		   					</c:choose>	                    
		                    <br>
		   					<label>Deadline : </label>
		   					<c:choose>
		   						<c:when test="${requestScope.user.invoice.duration < 0}">
		   						<c:out value="Unlimited"></c:out>
		   						</c:when>
		   						<c:otherwise>
		   							<c:out value="${requestScope.user.invoice.duration}"></c:out> days
		   						</c:otherwise>
		   					</c:choose>
		                    <br>
			            </div>
		            </div>
	            </div>
			
	    		<div class="col-sm-offset-2 col-sm-10">
	    			<a href="<%= request.getContextPath() %>/auth/editProfile?id=${requestScope.user.id}" class="btn btn-primary"> Edit profile</a>
	    		</div>
	    	</div>
	    	
		</div>
		<%@ include file="../template/footer.jsp" %>
	</body>
</html>