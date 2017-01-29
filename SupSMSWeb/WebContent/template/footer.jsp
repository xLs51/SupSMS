<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="footer">
  <div class="container">
   <jsp:useBean id="date" class="java.util.Date" />
   <p>SupSMS © <fmt:formatDate value="${date}" pattern="yyyy" /></p>
  </div>
 </div>