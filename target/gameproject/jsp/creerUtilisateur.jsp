<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création d'un compte</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/gamestyle.css"/>"/>
	</head>
	<body>
		<div>
			<form method="post" action="<c:url value="/servletCreationUtilisateur"/>" enctype="multipart/form-date">
				<fieldset>
					<legend>Informations compte</legend>
					
					<label for="identifiant">identifiant<span class="requis">*</span></label>
					<input type="text" id="identifiantUtilisateur" name="identifiantUtilisateur" value="<c:out value="${utilisateur.identifiantUtilisateur}"/>" size="30" maxlength="30" />
					<span class="erreur">${form.erreurs['identifiantUtilisateur']}</span>
				<br />
				
				<label for="mot_de_passe">mot de passe<span class="requis">*</span></label>
					<input type="text" id="mot_de_passeUtilisateur" name="mot_de_passeUtilisateur" value="<c:out value="${utilisateur.mot_de_passeUtilisateur}"/>" size="30" maxlength="30" />
				<br />
				
				<label for="email">email<span class="requis">*</span></label>
					<input type="text" id="emailUtilisateur" name="emailUtilisateur" value="<c:out value="${utilisateur.emailUtilisateur}"/>" size="30" maxlength="30" />
				<br />
				</fieldset>
				<p class="info">${form.resultat}</p>
				<input type="submit" value="Valider" />
				<input type="reset" value="Tout supprimer" /> <br />
			</form>
		</div>
	</body>
</html>