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
				<h1>Offers</h1>
			</div>
			
			<div class="row flat col-md-offset-3 col-md-12">
				<c:forEach items="${listOffers}" var="listOffers">
					<div class="panel price panel-blue col-md-3">
						<div class="panel-heading arrow_box text-center">
						<h3>${listOffers.name}</h3>
						</div>
						<div class="panel-body text-center">
							<p class="lead" style="font-size:40px"><strong>${listOffers.price} $</strong></p>
						</div>
						<ul class="list-group list-group-flush text-center">
							<c:choose>
			                    <c:when test="${listOffers.nbOfSMS < 0}">
			                    	<li class="list-group-item"><i class="glyphicon glyphicon-ok text-info"></i> Unlimited SMS</li>
			                    </c:when>
			                    <c:otherwise>
			                    	<li class="list-group-item"><i class="glyphicon glyphicon-ok text-info"></i> ${listOffers.nbOfSMS} SMS</li>
			                    </c:otherwise>
							</c:choose>
							
							<c:choose>
			                    <c:when test="${listOffers.duration < 0}">
			                    	<li class="list-group-item"><i class="glyphicon glyphicon-ok text-info"></i> Unlimited</li>
			                    </c:when>
			                    <c:otherwise>
			                    	<li class="list-group-item"><i class="glyphicon glyphicon-ok text-info"></i> ${listOffers.duration} days</li>
			                    </c:otherwise>
							</c:choose>
						</ul>
						<div class="panel-footer">
							<a class="btn btn-lg btn-block btn-primary" href="<%= request.getContextPath() %>/register?id=${listOffers.id}">BUY NOW !</a>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="row credit-card">
				<h4>Accepted Payment Methods :</h4>
				<ul style="margin-left: -40px;">
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/cb.png"></li>
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/visa.png"></li>
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/mastercard.png"></li>
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/amex.png"></li>
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/diners.jpg"></li>
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/discover.png"></li>
					<li><img alt="credit_card" src="<%= request.getContextPath() %>/img/jcb.png"></li>
				</ul>
			</div>

		</div>
		
		<%@ include file="template/footer.jsp" %>				
	</body>
</html>