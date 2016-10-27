<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add customer</title>
</head>
<body>
 
 
<form:form method="post" modelAttribute="customerForm" action="${customerActionUrl}">
		<form:label path="firstName"><spring:message code="label.customer.firstname" text="more default text" /></form:label>
		<form:input path="firstName" type="text" /> <!-- bind to first name-->
		<form:errors path="firstName" />
		<form:input path="lastName" type="text" /> <!-- bind to last name-->
		<form:errors path="lastName" />
		<form:input path="email" type="text" /> <!-- bind to email-->
		<form:errors path="email" />
		
	</form:form>


</body>
</html>