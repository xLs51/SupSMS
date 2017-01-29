<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<%@ include file="template/header.jsp" %>
	</head>
	<body>
		<%@ include file="template/navbar.jsp" %>
		
		<div class="container">
			<%@ include file="template/infoViewer.jsp" %>
			<div class="page-header">
				<h1>Log in</h1>
			</div>
			
				<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login">
			    	<div class="form-group">
			    		<label class="col-sm-2 control-label" for="inputUsername">Username</label>
			    		<div class="col-sm-3">
			    			<input class="form-control" type="text" id="inputUsername" placeholder="Username" name="username">
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label class="col-sm-2 control-label" for="inputPassword">Password</label>
			    		<div class="col-sm-3">
			    			<input class="form-control" type="password" id="inputPassword" placeholder="Password" name="password" pattern=".{8,}" title="8 characters minimum">
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<div class="col-sm-offset-2 col-sm-3">
			    			<button type="submit" class="btn btn-primary">Log in</button>
			    		</div>
			    	</div>
		   		</form>
		   		
		</div>
		
		<%@ include file="template/footer.jsp" %>				
	</body>
</html>