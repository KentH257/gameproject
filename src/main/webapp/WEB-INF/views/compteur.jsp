<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link href="<c:url value="/resources/css/compteur.css" />"
	rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p id="compte">0 €</p>
	<div id="test">
	
	<button id="start">Start</button>
	<button id="stop">Stop</button>
	<button id="save">Save</button>
	<p id="tirelire">Tirelire : 0 €</p>
</div>
	<script src="<c:url value="/resources/js/compteur.js" />"></script>
</body>
</html>