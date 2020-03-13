<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false"%>
<link href="<c:url value="/resources/css/menu.css" />"rel="stylesheet">
<div  id="menu">
 
  <a href="${pageContext.request.contextPath}/welcome">Home</a>
  
  <c:if test="${pageContext.request.userPrincipal.name == null }">
  | &nbsp;
     <a href="${pageContext.request.contextPath}/showForm">Inscription</a>
     
     | &nbsp;
     <a href="${pageContext.request.contextPath}/login">Login</a>
 </c:if>
  
  
  | &nbsp;
  
  <a href="${pageContext.request.contextPath}/admin">Admin</a>
  
  <c:if test="${pageContext.request.userPrincipal.name != null}">
  
     
     
     | &nbsp;
  
   <a href="${pageContext.request.contextPath}/userInfo">User Info</a>
     
     
     
     | &nbsp;
     <a href="${pageContext.request.contextPath}/logout">Logout</a>
  </c:if>
  
  <p id="date"></p>
  
</div>
<script src="<c:url value="/resources/js/currentDate.js" />"></script>