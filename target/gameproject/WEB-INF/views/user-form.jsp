<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC 5 - form handling | Java Guides</title>
<link href="<c:url value="/resource/css/bootstrap.min.css" />"
 rel="stylesheet">
<script src="<c:url value="/resource/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resource/js/bootstrap.min.js" />"></script>

</head>
<body>
 <div class="container">
  <div class="col-md-offset-2 col-md-7">
   <h3 class="text-center">Test formulaire</h3>
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Ajouter Utilisateur</div>
    </div>
    <div class="panel-body">
     <form:form action="saveUser" cssClass="form-horizontal"
      method="post" modelAttribute="user">

      <!-- need to associate this data with customer id -->
      <form:hidden path="id" />

      <div class="form-group">
       <label for="identifiant" class="col-md-3 control-label">
        Identifiant</label>
       <div class="col-md-9">
        <form:input path="identifiant" cssClass="form-control" />
       </div>
      </div>
      <div class="form-group">
       <label for="mot_de_passe" class="col-md-3 control-label">Mdp</label>
       <div class="col-md-9">
        <form:input path="mot_de_passe" cssClass="form-control" />
       </div>
      </div>

      <div class="form-group">
       <label for="email" class="col-md-3 control-label">Email</label>
       <div class="col-md-9">
        <form:input path="email" cssClass="form-control" />
       </div>
      </div>

      <div class="form-group">
       <!-- Button -->
       <div class="col-md-offset-3 col-md-9">
        <form:button cssClass="btn btn-primary">Submit</form:button>
       </div>
      </div>

     </form:form>
    </div>
   </div>
  </div>
 </div>
</body>
</html>