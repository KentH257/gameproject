<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create</title>
</head>
<body>
<h2>Inscription impossibe </h2> 

<h2> Identifiant et/ou email déjà enregistré !!</h2> 
<p><a href= "<c:url value ="http://localhost:8080/gameproject/login"/>">Page de connexion</a> </p>
<p><a href= "<c:url value ="http://localhost:8080/gameproject/showForm"/>">Page d'inscription</a></p>
</body>
