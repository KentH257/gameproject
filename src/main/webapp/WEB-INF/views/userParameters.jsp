<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>${title}</title>
<link href="<c:url value="/resources/css/parameters.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 


<h2>Mon compte :</h2>


<!-- test photo encore :	<img width="350" height="350" src='<c:out value="/gameproject/D:/gameproject_image/image/ultima/41wZOHbkeeL.jpg"/>'/>-->

<ul>
	<li> Ajout date de création </li>
	<li> Ajout autre information ? lesquelles ? </li>
</ul>

	<div id="photo">
 		<a href="/gameproject/<c:out value='${image}'/>"><img width="350" height="350" src="/gameproject/<c:out value='${image}'/>"></a>
		
	</div>
	<c:url var="updateImg" value="/updateImg">
        	<c:param name="utilisateurId" value="${id}" />
        	</c:url>
        	<a href="${updateImg}">Modifier votre image !</a>
	<br/><br/><br/><br/><br/><br/><br/><br/>
	<div id="tableau">
		<table border="1" width="200">
       	<!-- construct an "update" link with customer id -->
       		<c:url var="updateLink" value="/updateForm">
        	<c:param name="utilisateurId" value="${id}" />
       		</c:url>
		<tr>
			<th>Identifiant</th>
			<th>Email</th>
		</tr>
	<tr>
   		<td>${identifiant} 	</td>
   		<td>${email} 		</td>
  	</tr>
</table>


</div>
<div id="link">
	<a href="${updateLink}">Modifier les informations</a>
	</div>
	<c:if test="${param.error == 'true'}">
         <div id="TextError">
          
               	L'un des champs modifié n'est pas valide ! la modification n'a donc pas été effectué.
         </div>
    </c:if>

<h4><a href="http://localhost:8080/gameproject/userInfo">Retour</a></h4>
</body>
</html>