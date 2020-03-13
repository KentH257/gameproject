<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Utilisateur</title>
</head>
	<body>
		<div id="corps">
			<p>Identifiant : <c:out value="${utilisateur.identifiant }"/></p>
			<p>Email	   : <c:out value="${utilisateur.email }"/></p>
		</div>
		<fieldset>
		<legend>Menu</legend>
		<div>
		<p> ici on affiche / confirme les informations de l'utilisateur enregistré </p>
		
		<p> renvoi vers les différentes pages </p>
		<p>  => Modification des informations</p>
		<p>  => Ajout de document / image / video </p>
		<p>  => Creation d'un personnage </p>
		<p>  <a href="<c:url value="http://localhost:8080/gameproject/jsp/listeUtilisateur.jsp"/>">Voir la liste des Utilisateurs</a></p>
	
		</div>
		</fieldset>
	</body>
</html>