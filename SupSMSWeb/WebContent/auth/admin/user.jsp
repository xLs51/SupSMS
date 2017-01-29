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
				<h1>Users</h1>
			</div>
			
			<c:choose>
				<c:when test="${empty listUser}">There is no user !</c:when>
				<c:otherwise>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Id</th>
								<th>Username</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUser}" var="listUser">
								<tr>
				                   	<td>${listUser.id}</td>
				                   	<td>${listUser.username}</td>
				                   	<td>			                   		
				                   		<a href="<%= request.getContextPath() %>/auth/profile?id=${listUser.id}" class="btn btn-default">View Profile</a>
				                   		<a href="<%= request.getContextPath() %>/auth/editProfile?id=${listUser.id}" class="btn btn-primary">Edit Profile</a>
				                   		<a href="<%= request.getContextPath() %>/auth/admin/removeUser?id=${listUser.id}" class="btn btn-danger">Delete</a>
				                   	</td>
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