<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>${title}</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
 
    <h1>${message}</h1>
   
   <p>Ici on regroupe les informations propre à l'utilisateur tel que : </p>
    <ul>
    <li>Lien vers le personnage</li>
    <li>Temps total connecter</li>
    </ul>
    
    <a href="http://localhost:8080/gameproject/userInfo/parameters">Paramètres</a>
</body>
</html>