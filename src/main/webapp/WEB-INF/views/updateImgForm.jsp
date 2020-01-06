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
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
<jsp:include page="_menu.jsp" />
	
 <div class="container">
  <div class="col-md-offset-2 col-md-7">
   <h3 class="text-center">Test formulaire</h3>
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Ajouter Utilisateur</div>
    </div>
    <div class="panel-body">
     <form:form action="saveUpdateImg" cssClass="form-horizontal"
      method="post" modelAttribute="utilisateurImage" enctype="multipart/form-data">

      <!-- need to associate this data with customer id -->
      <form:hidden path="id" />

      <div class="form-group">
      <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
          
               L'un de ces champs n'est pas conforme !
         </div>
        
    </c:if>
    </div>
	<c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
          
               Fichier introuvable (.jpg/.png/.gif/.bmp)
         </div>
    </c:if>
		<div class="form-group">
		<label for="photo" class="col-md-3 control-label">Photo</label> <input path="imagePath" accept="image/*" type="file" name="file">
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