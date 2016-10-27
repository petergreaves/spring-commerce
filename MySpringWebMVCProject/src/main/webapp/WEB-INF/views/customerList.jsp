<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers</title>
</head>
<body>

<c:if test="${fn:length(customers) gt 0}">
<table>

<c:forEach items="${customers}" var="customer"> 

  <tr>
    <td>${customer.ID}</td> 
    <td>${customer.firstName}</td>
    <td>${customer.lastName}</td>   
    <td>${customer.email}</td> 
  </tr>
</c:forEach>
</table>
</c:if>

<spring:url value="/customer/${customer.ID}" var="customerUrl" />
<spring:url value="/customer/${customer.ID}/delete" var="deleteUrl" />
<spring:url value="/customer/${customer.ID}/update" var="updateUrl" />
<spring:url value="/customer/add" var="addUrl" />


<button class="btn btn-info"
        onclick="location.href='${addUrl}'">Add</button>

<button class="btn btn-primary"
        onclick="location.href='${updateUrl}'">Update</button>
<button class="btn btn-danger"
        onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>


</body>
</html>