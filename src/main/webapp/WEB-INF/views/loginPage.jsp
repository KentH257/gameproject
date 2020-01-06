<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<html>
<head>
<%@ page isELIgnored="false"%>
<title>Login</title></head>
<body>
   <jsp:include page="_menu.jsp" />
    
    
   <h1>Login</h1>
     
     <!-- /login?error=true -->
     <c:if test="${param.error == 'true'}">
         <div style="color:red;margin:10px 0px;">
          
                Login Failed!!!<br />
                Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                 
         </div>
    </c:if>
       
   <h3>Enter user name and password:</h3>  
     
   <form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
      <table>
         <tr>
            <td>Identifiant :</td>
            <td><input type='text' name='identifiant' value=''></td>
         </tr>
         <tr>
            <td>Mot de passe:</td>
            <td><input type='password' name='mot_de_passe' /></td>
         </tr>
         <tr>
            <td><input name="Valider" type="submit" value="Valider" /></td>
         </tr>
      </table>
  </form>
</body>
</html>