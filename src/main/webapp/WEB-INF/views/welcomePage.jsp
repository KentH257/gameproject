<%@page session="false"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>${title}</title>
</head>
<body>
<jsp:include page="_menu.jsp" />

  <h1>${message}</h1>
  
  <p>Ici on ajoute les infos g�n�ral du site</p>
  
  <ul>
  <li>Son utilit� / possibilit�</li>
  <li>Son futur possible ? </li>
  <li>Nombre total d'inscrit / et connect� � l'instant T </li>
  <li>un fond d'�cran basique mais efficace ? dynamique ? </li>
  <li>Fil d'actualit�</li>
  </ul>
  
</body>
</html>