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
     
     <p> Elle n'a pas de but pr�cis pour le moment, juste la possibilit� d'acc�der aux donn�es des diff�rents utilisateurs</p>
     <p> Par la suite l'admin ne pourras plus voir le mot de passe pour assur� s�curit� et confidentialit� ! </p>
</body>
</html>