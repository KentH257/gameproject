<%@page session="false"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>${title}</title>
</head>
<body>
<jsp:include page="_menu.jsp" />

  <h1>${message}</h1>
  
  <p>Ici on ajoute les infos général du site</p>
  
  <ul>
  <li>Son utilité / possibilité</li>
  <li>Son futur possible ? </li>
  <li>Nombre total d'inscrit / et connecté à l'instant T </li>
  <li>un fond d'écran basique mais efficace ? dynamique ? </li>
  <li>Fil d'actualité</li>
  </ul>
  
</body>
</html>