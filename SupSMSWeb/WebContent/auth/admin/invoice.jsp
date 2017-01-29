<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<%@ include file="../../template/header.jsp" %>
	</head>
	<body>
		<%@ include file="../../template/navbar.jsp" %>
		
		<div class="container">
			<%@ include file="../../template/infoViewer.jsp" %>
			<div class="page-header">
				<h1>Invoices</h1>
			</div>
			
			<c:choose>
				<c:when test="${empty listInvoice}">There is no invoice !</c:when>
				<c:otherwise>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Price</th>
								<th>Duration</th>
								<th>Nb of SMS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listInvoice}" var="listInvoice">
								<tr>
				                   	<td>${listInvoice.id}</td>
				                   	<td>${listInvoice.name}</td>
				                   	<td>${listInvoice.price}</td>
				                   	<td>${listInvoice.duration}</td>
				                   	<c:choose>
					                   	<c:when test="${listInvoice.nbOfSMS < 0}">
					                   		<td>Unlimited</td>
					                   	</c:when>
					                   	<c:otherwise>
					                   		<td>${listInvoice.nbOfSMS}</td>
					                   	</c:otherwise>
				                   	</c:choose>				                   	
				               	</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		
		<%@ include file="../../template/footer.jsp" %>				
	</body>
</html>