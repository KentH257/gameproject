<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs existants</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/gamestyle.css"/>" />
</head>
<body>
	<div id="corps">
	<form method="get" action="<c:url value="/servletListeUtilisateur"/>" enctype="multipart/form-date">
		<c:choose>
			<%-- Si aucun utilisateur n'existe en session, affichage d'un message par défaut --%>
			<c:when test="${ empty sessionScope.utilisateurs }">
				<p class="erreur">Aucun utilisateur enregistré</p>
			</c:when>
			<%-- Sinon, affichage du tableau --%>
			<c:otherwise>
				<table>
					<tr>
						<th>Id</th>
						<th>Identifiant</th>
						<th>Email</th>
						<th>M.D.P</th>
						<th>Supprimer</th>
					</tr>
			
					<%-- Parcours de la Map des utilisateurs en session, et utilisation de l'objet varStatus --%>
					
					<c:forEach items="${sessionScope.utilisateurs}" var="utilisateurs" varStatus="boucle">
						<%-- Simple test de parité sur l'index de pacours, pour alterner la couleur de fond de chaque ligne du tableau --%>
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair' }">
							
							<%-- Affichage des propriétés du bean Utilisateur, qui est stocké en tant que valeur de l'entrée courante de la map --%>
							<td><c:out value="${utilisateurs.value.id }"></c:out></td>
							<td><c:out value="${utilisateurs.value.identifiant}"></c:out></td>
							<td><c:out value="${utilisateurs.value.email }"></c:out></td>
							<td><c:out value="${utilisateurs.value.mot_de_passe }"></c:out></td>	
							<%-- Lien vers la servlet de suppression, avec passage du nom de l'utilisateur c-a-d la clé de la Map en paramètre grâce à la balise <c:param> --%>
							<td class="action"><a href="<c:url value="/servletSuppressionUtilisateur"><c:param name="idUtilisateur" value="${utilisateurs.key }" ></c:param></c:url>">
									<img src="<c:url value="/inc/supprimer.png"></c:url>" alt="Supprimer" />
							</a></td>
						</tr>
					</c:forEach>
					
				</table>
			</c:otherwise>
		</c:choose>
	</form>
	</div>
</body>
</html>