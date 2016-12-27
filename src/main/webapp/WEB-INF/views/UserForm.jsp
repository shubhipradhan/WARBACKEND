<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New or Edit User</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit User</h1>
		<table>
		<c:url var="addAction" value="/save" ></c:url>
			<form:form action="addAction" method="post" modelAttribute="user">
			<form:hidden path="id"/>
			<tr>
				<td>Username:</td>
				<td><form:input path="username"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><form:input path="address"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Save">
				</td>
			</tr>			
			</form:form>
		</table>
	</div>
	
</body>
</html>