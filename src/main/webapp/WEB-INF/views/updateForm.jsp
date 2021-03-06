<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ page isELIgnored="false"%>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
 rel="stylesheet">
 <link href="<c:url value="/resources/css/userForm.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
<jsp:include page="_menu.jsp" />
	
 <div class="container">
  <div class="col-md-offset-2 col-md-7">
   <h3 class="text-center">Modifier vos informations</h3>
   <div class="panel panel-info" id="panel">
    <div class="panel-heading">
     <div class="panel-title">Utilisateur</div>
    </div>
    <div class="panel-body">
     <form:form action="saveUpdate" cssClass="form-horizontal"
      method="post" modelAttribute="utilisateur" enctype="multipart/form-data">

      <!-- need to associate this data with customer id -->
      <form:hidden path="id" />
	<form:hidden path="mot_de_passe" />
	
      <div class="form-group">
      <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
          
               L'un de ces champs n'est pas conforme !
         </div>
    </c:if>
       <label for="identifiant" class="col-md-3 control-label">
        Identifiant</label>
       <div class="col-md-9">
        <form:input path="identifiant" cssClass="form-control" />
        <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
          
               Doit contenir entre 4 et 15 caractères ( ex : Test)
         </div>
    </c:if>
       </div>
      </div>

      <div class="form-group">
       <label for="email" class="col-md-3 control-label">Email</label>
       <div class="col-md-9">
        <form:input path="email" cssClass="form-control" />
        <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
          
               	Email non valide ( ex : test@test.com)
         </div>
    </c:if>
       </div>
      </div>
	
      <div class="form-group">
       <!-- Button -->
       <div class="col-md-offset-3 col-md-9">
        <form:button cssClass="btn btn-primary">Valider</form:button>
        <input type="reset" value="Tout supprimer" />
       </div>
      </div>

     </form:form>
    </div>
   </div>
  </div>
 </div>
</body>
</html>