<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/general.css" />"
	rel="stylesheet">
<html>
<head>
<title>Logout</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 <div id="GreenBg">
    <h2>Vous vous �tes d�connect�, A bient�t !</h2>
    
    <ul>
    <li> centrer le texte => milieu haut</li>
    </ul>
    
    <a href= "<c:url value ="http://localhost:8080/gameproject/welcome"/>">Retour � la page d'accueil !</a> 
    <br/>
    <br/>
    <a href= "<c:url value ="http://localhost:8080/gameproject/login"/>">Retour � la page de connexion !</a>
    </div>
</body>
</html>