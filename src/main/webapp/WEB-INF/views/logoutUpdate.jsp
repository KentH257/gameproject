<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Logout</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
    <h2>Votre compte à bien été mis à jour !</h2>
    
    <p> Veuillez vous reconnectez en cliquant juste <a href= "<c:url value ="http://localhost:8080/gameproject/login"/>">ici</a> ! </p>
    
        <ul>
    <li> centrer le texte => milieu haut</li>
    </ul>
    
</body>
</html>