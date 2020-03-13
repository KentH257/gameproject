<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste Utilisateur</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
 rel="stylesheet">
 <link href="<c:url value="/resources/css/general.css" />"
	rel="stylesheet">
<%@ page isELIgnored="false"%>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<jsp:include page="_menu.jsp" />
 <div class="container">
  <div class="col-md-offset-1 col-md-10">
   <h3 class="text-center">Liste des utilisateurs</h3>
   <hr />

   <input type="button" value="Add User"
    onclick="window.location.href='showForm'; return false;"
    class="btn btn-primary" /> <br />
   <br />
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Liste Utilisateurs</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>Identifiant</th>
       <th>Mot de passe</th>
       <th>Email</th>
       <th>Action</th>
      </tr>

      <!-- loop over and print our customers -->
      <c:forEach var="tempUtilisateur" items="${utilisateurs}">

       <!-- construct an "update" link with customer id -->
       <c:url var="updateLink" value="/updateForm">
        <c:param name="utilisateurId" value="${tempUtilisateur.id}" />
       </c:url>

       <!-- construct an "delete" link with customer id -->
       <c:url var="deleteLink" value="/delete">
        <c:param name="utilisateurId" value="${tempUtilisateur.id}" />
       </c:url>

       <tr>
        <td>${tempUtilisateur.identifiant}</td>
        <td>${tempUtilisateur.mot_de_passe}</td>
        <td>${tempUtilisateur.email}</td>

        <td>
         <!-- display the update link --> <a href="${updateLink}" id="UaD">Update</a>
          <a href="${deleteLink}" id="UaD"
         onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
        </td>

       </tr>

      </c:forEach>

     </table>

    </div>
   </div>
  </div>

 </div>
 <div class="footer">
  <p>La on peut mettre des infos suppl @Entertainment 2005 Inc Production Management</p>
 </div>
</body>

</html>