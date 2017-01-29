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
				<h1>Add a contact</h1>
			</div>
			
				<form class="form-horizontal col-md-6" method="post" action="${pageContext.request.contextPath}/auth/addContact" role="form">
			    	<div class="form-group">
			    		<label for="inputFirstname" class="col-sm-4 control-label">Firstname</label>
			    		<div class="col-sm-6">
			    			<input class="form-control" type="text" id="inputFirstname" placeholder="Firstname" name="firstname" required>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="inputLastname" class="col-sm-4 control-label">Lastname</label>
			    		<div class="col-sm-6">
			    			<input class="form-control" type="text" id="inputLastname" placeholder="Lastname" name="lastname" required>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="inputPhone" class="col-sm-4 control-label">Phone number</label>
			    		<div class="col-sm-6">
			    			<input class="form-control" type="text" id="inputPhone" placeholder="Phone number" name="phone" required>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="inputMail" class="col-sm-4 control-label">E-Mail</label>
			    		<div class="col-sm-6">
			    			<input class="form-control" type="email" id="inputMail" placeholder="E-Mail" name="email" required>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label class="col-sm-4 control-label"></label>
					   	<div class="col-sm-6">
					     	<button type="submit" class="btn btn-primary ">Add</button>
					    </div>
					</div>
			    </form>
			 	<div class="col-md-3">
			 		<h4>Or search a contact :</h4>
					<input type="text" id="search" class="form-control" placeholder="Search for a user"><br/>
					<select id="select-user" class="form-control" style="display:none;">
					</select>
				</div>

		</div>
		<span id="currentUsername" style="display: none">${sessionScope.user.username}</span>
		<script type="text/javascript">
			var currentUsername = $("#currentUsername").text();
			
			$("#search").keyup(function() {
				$.ajax({
	    			url: '/SupSMSWeb/auth/searchContact',
	    			type: 'POST',
	    			data: 'searchByName=' + $(this).val(),
	    			dataType: 'json',
	    			success: function(response) {
						var html = null;
		            	
		            	$.each(response , function(i, user) {
		            		if(currentUsername != user.username) {
		            			html += "<option value='" + user.id + "'>" + user.username + "</option>";
		            		}
		            	});
		            	
		            	$('#select-user').show();
		            	
		            	if(html == null)
		            		$('#select-user').html('<option value="0">There is no contact !</option>');
		            	else {
		            		$('#select-user').html('<option value="0">Choose a contact</option>');
		            		$('#select-user').append(html);
		            	}
	    			}
	    		});
			});
		    
		    $('#select-user').change(function() {
		    	if($(this).val() != 0) {
		    		$.ajax({
		    			url: '/SupSMSWeb/auth/searchContact',
		    			type: 'POST',
		    			data: 'searchById=' + $(this).val(),
		    			dataType: 'json',
		    			success: function(response) {
		    				if(response.res == "0") {
		    					alert('hacker');
		    				}
		    				else {
		    					$('#inputFirstname').val(response.firstname);
		    					$('#inputLastname').val(response.lastname);
		    					$('#inputPhone').val(response.phone);
		    					$('#inputMail').val(response.mail);
		    				}
		    			}
		    		});
		    	}
		    });
		</script>
		<%@ include file="../template/footer.jsp" %>				
	</body>
</html>