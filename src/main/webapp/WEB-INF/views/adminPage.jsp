<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 <%@ page isELIgnored="false"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
    <h2>Admin Page</h2>
 
 
    <h3>Bonjour : ${pageContext.request.userPrincipal.name} !</h3>
 
    <b>Cette page est uniquement accessible aux admins !</b>
    
    | &nbsp;
     <a href="${pageContext.request.contextPath}/list">Liste user</a>  
     
     <p> Elle n'a pas de but précis pour le moment, juste la possibilité d'accéder aux données des différents utilisateurs</p>
     <p> Par la suite l'admin ne pourras plus voir le mot de passe pour assuré sécurité et confidentialité ! </p>
</body>
</html>