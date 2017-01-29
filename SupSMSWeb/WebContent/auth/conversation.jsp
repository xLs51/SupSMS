<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
				<h1>Conversation with <c:out value="${ requestScope.conversation.contactName }"></c:out>
				- Total SMS: <c:out value="${ fn:length(requestScope.sms) }"></c:out>
				</h1>
			</div>
			
			<div class="smsContainer well" style="display: table; margin: auto; width: 540px;">
				<c:forEach items="${ requestScope.sms }" var="currentSMS">
					<c:choose>
						<c:when test="${ currentSMS.sender.id == sessionScope.user.id }">
							<div class="conversation conversation-right">
								<p><c:out value="${ currentSMS.text }"></c:out></p>
								<p><c:out value="${ currentSMS.date }"></c:out></p>
							</div>
							<div class="conversation-hide conversation-hide-left"></div>
						</c:when>
						<c:otherwise>
							<div class="conversation conversation-left">
								<p><c:out value="${ currentSMS.text }"></c:out></p>
								<p><c:out value="${ currentSMS.date }"></c:out></p>
							</div>
							<div class="conversation-hide conversation-hide-left"></div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/auth/newMessage">
	    		<label class="col-sm-offset-2 col-sm-2 control-label" for="inputMessage" style="margin-top: 20px;">Message</label>
	    		<div class="col-sm-3" style="margin-top: 20px;">
	    			<input class="form-control" type="text" id="inputMessage" placeholder="Message" name="message">
	    		</div>
		    	<div class="col-sm-2" style="margin-top: 20px;">
			    	<input type="hidden" name="contact" value="${ requestScope.conversationNumber }" />
			    	<input type="submit" value="Send" class="btn btn-primary btn-block"/>
			    </div>
		    </form>
		</div>
		
		<%@ include file="../template/footer.jsp" %>				
	</body>
</html>