<%@page session="false"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>Access Denied</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
 
    <h3 style="color:red;">${message}</h3>
</body>
</html>