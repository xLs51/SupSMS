<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<%
   request.setAttribute("pageTitle", "Welcome");
 %>
<%@ include file="template/header.jsp"%>
</head>
<body>
	<%@ include file="template/navbar.jsp"%>

	<div class="container">
		<%@ include file="template/infoViewer.jsp"%>
        
		<c:choose>
			<c:when test="${ sessionScope.user != null }">
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<ul class="event-list">
							<c:forEach items="${requestScope.conversations}" var="currentConversation">
								<li>
									<a class="delete" href="<%= request.getContextPath() %>/auth/removeConversation?conversation=${currentConversation.id}"><button type="button" class="close" style="float:right;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></a>
									<div class="comment">
										<a href="<%= request.getContextPath() %>/auth/conversation?id=${currentConversation.id}"><span class="glyphicon glyphicon-comment glyphicon-4x glyphicon25px"></span></a>
									</div>
									<div class="info">
										<h2 class="title">Conversation :</h2>
										<p class="desc"><c:out value="${currentConversation.contactName}"></c:out></p>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-offset-2 col-md-8">
		            <div class="box">
		                <div class="box-icon">
		                    <span class="glyphicon glyphicon-comment glyphicon-4x"></span>
		                </div>
		                <div class="info">
		                    <h4 class="text-center">SupSMS</h4>
		                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti atque, tenetur quam aspernatur corporis at explicabo nulla dolore necessitatibus doloremque exercitationem sequi dolorem architecto perferendis quas aperiam debitis dolor soluta!</p>
		                    
		                    <div class="col-md-4">
					            <div class="panel status panel-primary">
					                <div class="panel-heading">
					                    <h1 class="panel-title text-center"><c:out value="${requestScope.numberOfUsers}"></c:out></h1>
					                </div>
					                <div class="panel-body text-center">                        
					                    <strong>Users</strong>
					                </div>
					            </div>
					        </div>
					        
					        <div class="col-md-offset-4 col-md-4">
					            <div class="panel status panel-primary">
					                <div class="panel-heading">
					                    <h1 class="panel-title text-center"><c:out value="${requestScope.numberOfSMS}"></c:out></h1>
					                </div>
					                <div class="panel-body text-center">                        
					                    <strong>SMS</strong>
					                </div>
					            </div>
					        </div>
		                    
		                    <a href="<%= request.getContextPath() %>/register" class="btn btn-primary">Register now !</a>
		                </div>
		            </div>
		        </div>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="template/footer.jsp"%>
</body>
</html>