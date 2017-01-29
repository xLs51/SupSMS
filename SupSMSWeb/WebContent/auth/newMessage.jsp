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
				<h1>Contacts</h1>
			</div>
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/auth/newMessage">
		    	<div class="form-group">
		    		<label class="col-sm-2 control-label" for="inputContact">Contact</label>
		    		<div class="col-sm-3">
		    			<select class="form-control" id="selectContact">
		    				<option></option>
		    				<c:forEach items="${ requestScope.listContact }" var="listContact">
		    					<option value="<c:out value="${ listContact.phone }"></c:out>"><c:out value="${ listContact.firstName }"></c:out> <c:out value="${ listContact.lastName }"></c:out></option>
		    				</c:forEach>
		    			</select><br/>
		    			<input type="text" class="form-control" id="inputContact" name="contact" placeholder="Contact" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label class="col-sm-2 control-label" for="inputMessage">Message</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputMessage" placeholder="Message" name="message" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<div class="col-sm-offset-2 col-sm-3">
		    			<button type="submit" class="btn btn-primary">Send</button>
		    		</div>
		    	</div>
	   		</form>
		</div>
		<script type="text/javascript">
			$( document ).ready(function() {
				$('#selectContact').change(function() {
					$('#inputContact').val($(this).val());
				});	
			});
		</script>
		<%@ include file="../template/footer.jsp" %>				
	</body>
</html>