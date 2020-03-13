<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="<c:url value="/resources/css/general.css" />"
	rel="stylesheet">
	<script src="<c:url value="/resources/js/general.js" />"></script>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>${title}</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
 <div id="GreenBg">
    <h1>${message}</h1>
   
   <p>Ici on regroupe les informations propre à l'utilisateur tel que : </p>
    <ul>
    <li>Lien vers le personnage</li>
    <li>Temps total connecter</li>
    </ul>
    
    <a href="http://localhost:8080/gameproject/userInfo/parameters">Paramètres</a>
    <button onclick="confirm()">
    <a href="http://localhost:8080/gameproject/userInfo/ffGame">Demo Final Fantasy Remake</a>
    </button>
    <a href="http://localhost:8080/gameproject/userInfo/compteur">Compteur virtuel</a>
    </div>
</body>
</html>