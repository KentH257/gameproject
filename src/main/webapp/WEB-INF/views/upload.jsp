<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>${title}</title>
</head>
<body>
<form:form action="uploadFile" cssClass="form-horizontal"
      method="post" modelAttribute="imagePath" enctype="multipart/form-data">
      
		File to upload: <input type="file" name="file"><br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Submit" />
	
	</form:form>
</body>
</html>