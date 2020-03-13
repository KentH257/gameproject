<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<link href="<c:url value="/resources/css/welcome.css" />"
	rel="stylesheet">
<title>${title}</title>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<section>
		<h1>${message}</h1>

		<p>Ici on ajoute les infos général du site</p>
		
		<h1> Bienvenue à vous !</h1>
		<p> Vous vous êtes retrouvé sur ce site par erreur ou même par hasard ? C'est tant mieux !</p>
		<p> Ce site n'a aucun but hormis celui de regrouper toute mes connaissances dans le développement ! (donc il a un but..non ?)</p>
		<p> et vous ? Eh bien vous êtes <strong>mes testeur et commentateur !</strong></p>
		<p> Il y a un bug ici ! </p>
		<p> Est-ce qu'une page météo pourrais exister ? </p>
		<p> Je peux me créer un album photo ? </p>
		<p> I don't speak french, is it possible to include an auto translation of each page ?</p>
		<h3>Je prends note, je construis, je développe ! ( du moins essayer pour combler vos attentes !)</h3>
		
		<p> Vous êtes intéressé ? direction Inscription -> Login -> Eeeeet c'est pas encore crée
		 
		 <h4>Développement à faire</h4>
		<ul>
			<li>Nombre total d'inscrit / et connecté à l'instant T sur la page d'accueil</li>
			<li> Fil d'actualité</li>
			<li> Ajout d'un swap Langage FR/EN </li>
			<li> Un chat discussion général instantanée </li>
			<li> Un mini-jeu rpg (style jeu flash) </li>
		</ul>
	</section>
	<img src="image/inProgress.webp" id="working" />

</body>
</html>