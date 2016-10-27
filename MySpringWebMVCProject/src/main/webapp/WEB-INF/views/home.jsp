<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<spring:url value="/customer/list"/ var="listURL" />
<html>
<head>
	<title>Home</title>  
</head>
<body>
Here's the<a href="${listURL}"> customer list</a>.
</body>
</html>
