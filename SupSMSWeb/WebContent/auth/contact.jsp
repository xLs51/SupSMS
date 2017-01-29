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
			
			<c:if test="${empty listContact}">You don't have any contact !<br><br></c:if>
			<a class="btn btn-primary" href="<%= request.getContextPath() %>/auth/addContact">Add a contact</a><br><br>
			<c:if test="${!empty listContact}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Phone Number</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" />
						<c:forEach items="${listContact}" var="listContact">
							<tr>
			               		<td>${count}</td>
			                   	<td class="col-md-3">${listContact.firstName}</td>
			                   	<td class="col-md-3">${listContact.lastName}</td>
			                   	<td class="col-md-3">${listContact.phone}</td>
			                   	<td><a href="<%= request.getContextPath() %>/auth/updateContact?id=${listContact.id}" class="btn btn-success">Update</a> <a href="<%= request.getContextPath() %>/auth/removeContact?id=${listContact.id}" class="btn btn-danger">Delete</a></td>
			               	</tr>
			               	<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		
		<%@ include file="../template/footer.jsp" %>				
	</body>
</html>