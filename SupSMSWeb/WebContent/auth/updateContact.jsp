<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<h1>Update Contact</h1>
			</div>
	    	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/auth/updateContact" role="form">
		    	<div class="form-group">
		    		<label for="inputFirstname" class="col-sm-2 control-label">Firstname</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputFirstname" placeholder="Firstname" name="firstname" value="${contact.firstName}" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputLastname" class="col-sm-2 control-label">Lastname</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputLastname" placeholder="Lastname" name="lastname" value="${contact.lastName}" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputMail" class="col-sm-2 control-label">E-Mail</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="email" id="inputMail" placeholder="E-Mail" name="email" value="${contact.mail}" required>
		    		</div>
		    	</div>
		    	<input type="hidden" name="id" value="${contact.id}">
	    		<div class="form-group">
				   	<div class="col-sm-offset-2 col-sm-10">
				     	<button type="submit" class="btn btn-primary ">Update</button>
				    </div>
				</div>
	    	</form>
		</div>

		<%@ include file="../template/footer.jsp" %>		
	</body>
</html>