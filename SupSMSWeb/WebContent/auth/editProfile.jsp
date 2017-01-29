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
				<h1>Edit profile</h1>
			</div>
			
			<form method="POST" action="${pageContext.request.contextPath}/auth/editProfile" class="form-horizontal" role="form">
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for=oldPassword>Current Password</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" autofocus="autofocus" type="password" id="oldPassword" placeholder="Current Password" name="oldPassword" pattern=".{8,}" title="8 characters minimum">
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for=password>New Password</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="password" id="password" placeholder="New Password" name="password" pattern=".{8,}" title="8 characters minimum">
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for="passwordConfirm">Password confirmation</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="password" id="passwordConfirm" placeholder="Repeat new password" name="passwordConfirm" pattern=".{8,}" title="8 characters minimum">
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for="firstName">Firstname</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="text" id="firstName" placeholder="First name" name="firstname">
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for="lastName">Lastname</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="text" id="lastName" placeholder="Last name" name="lastname">
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for="phone">Phone number</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="text" id="phone" placeholder="Phone number" name="phone">
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label class="col-sm-2 control-label" for="creditCard">Credit card number</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="password" id="creditCard" placeholder="Credit card number" name="creditCard">
	    			</div>
	    		</div>
	    		<div class="form-group">
					<label class="col-sm-2 control-label" for="Mail">E-mail</label>
	    			<div class="col-sm-3">
	    				<input class="form-control" type="email" placeholder="Email" name="email" >
	    			</div>
	    		</div>
				<div class="form-group">
					<c:if test="${requestScope.user.invoice != null}">
						<c:forEach items="${ requestScope.listInvoices }" var="currentInvoice" varStatus="status">						
								<div class="col-sm-4">
									<c:choose>
								    	<c:when test="${currentInvoice.id == requestScope.user.invoice.id }">
								      		<div class="panel panel-primary">
									    </c:when>					
										<c:otherwise>
											<div class="panel panel-default">
										</c:otherwise>
									</c:choose>
							      	<div class="panel-heading">
							        	<h3 class="panel-title"><c:out value="${currentInvoice.name}"></c:out></h3>
							      	</div>
									<div class="panel-body alignCenter">
							        	<p style="font-size: 50px; font-weight: bold;"><c:out value="${currentInvoice.price}"></c:out>$</p>
										<div style="font-size: 17px;">
											<label>Number of SMS : </label>
											<c:choose>
						   						<c:when test="${currentInvoice.nbOfSMS < 0}">
						   							<c:out value="Unlimited"></c:out>
						   						</c:when>
						   						<c:otherwise>
						   							<c:out value="${currentInvoice.nbOfSMS}"></c:out> SMS
						   						</c:otherwise>
						   					</c:choose>	 						                    
						                    <br>
						   					<label>Deadline : </label>
						   					<c:choose>
						   						<c:when test="${currentInvoice.duration < 0}">
						   						<c:out value="Unlimited"></c:out>
						   						</c:when>
						   						<c:otherwise>
						   							<c:out value="${currentInvoice.duration}"></c:out> days
						   						</c:otherwise>
						   					</c:choose>
						                    <br>
										</div>
										<c:choose>
									    	<c:when test="${currentInvoice.id == requestScope.user.invoice.id }">
									        	<input type="radio" name="invoice" value="${requestScope.user.invoice.id}" checked/>
										    </c:when>					
											<c:otherwise>
												<input type="radio" name="invoice" value="${currentInvoice.id}" />
											</c:otherwise>
										</c:choose>
							      	</div>
							    </div>
							</div>
						</c:forEach>
					</c:if>						
				</div>
				
				<div class="form-group">
				   	<div class="col-sm-offset-2 col-sm-10">
	   					<button type="submit" class="btn btn-primary">Save informations</button>
	   				</div>
	   			</div>
	   		</form>
		</div>
		
		<%@ include file="../template/footer.jsp" %>	
	</body>
</html>