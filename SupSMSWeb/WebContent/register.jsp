<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<h1>Sign in</h1>
			</div>
	    	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/register" role="form">
	    		<div class="form-group">
		    		<label for="inputPseudo" class="col-sm-2 control-label">Username</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" autofocus="autofocus" type="text" id="inputUsername" placeholder="username" name="username" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputPassword" class="col-sm-2 control-label">Password</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="password" id="inputPassword" placeholder="Password" name="password" pattern=".{8,}" title="8 characters minimum" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputPassword" class="col-sm-2 control-label">Password confirmation</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="password" id="inputPassword" placeholder="Password" name="passwordConfirm" pattern=".{8,}" title="8 characters minimum" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputFirstname" class="col-sm-2 control-label">Firstname</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputFirstname" placeholder="Firstname" name="firstname" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputLastname" class="col-sm-2 control-label">Lastname</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputLastname" placeholder="Lastname" name="lastname" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputPhone" class="col-sm-2 control-label">Phone number</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputPhone" placeholder="Phone number" name="phone" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputCreditCard" class="col-sm-2 control-label">Credit card number</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="text" id="inputCreditCard" placeholder="Credit card" name="creditCard" required>
		    		</div>
		    	</div>
		    	<div class="form-group">
		    		<label for="inputMail" class="col-sm-2 control-label">E-Mail</label>
		    		<div class="col-sm-3">
		    			<input class="form-control" type="email" id="inputMail" placeholder="E-Mail" name="email" required>
		    		</div>
		    	</div>
		    	
		    	<c:if test="${ !empty requestScope.invoices }">
					<c:forEach items="${requestScope.invoices}" var="currentInvoice">
				    	<div class="col-sm-4">
					    	<div class="panel panel-primary">
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
									<input type="radio" name="invoice" value="${currentInvoice.id}" checked/>
						      	</div>
						    </div>
						</div>
					</c:forEach>
		    	</c:if>
		    	
		    	
	    		<div class="form-group">
				   	<div class="col-sm-offset-2 col-sm-10">
				     	<button type="submit" class="btn btn-primary ">Sign in</button>
				    </div>
				</div>
	    	</form>
		</div>

		<%@ include file="template/footer.jsp" %>		
		
		<script src="${pageContext.request.contextPath}/js/register/invoiceAutoCheck.js"></script>	
	</body>
</html>