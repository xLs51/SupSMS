<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.errorMsg != null}">
	<div class="msg">
		<div class="alert alert-danger">
			<c:out value="${sessionScope.errorMsg}"></c:out>
			<% session.removeAttribute("errorMsg"); %>
		</div>
	</div>
</c:if>
<c:if test="${sessionScope.warningMsg != null}">
	<div class="msg">
		<div class="alert alert-success">
			<c:out value="${sessionScope.warningMsg}"></c:out>
			<% session.removeAttribute("warningMsg"); %>
		</div>
	</div>
</c:if>
<c:if test="${sessionScope.infoMsg != null}">
	<div class="msg">
		<div class="alert alert-success">
			<c:out value="${sessionScope.infoMsg}"></c:out>
			<% session.removeAttribute("infoMsg"); %>
		</div>
	</div>
</c:if>
<c:if test="${sessionScope.successMsg != null}">
	<div class="msg">
		<div class="alert alert-success">
			<c:out value="${sessionScope.successMsg}"></c:out>
			<% session.removeAttribute("successMsg"); %>
		</div>
	</div>
</c:if>