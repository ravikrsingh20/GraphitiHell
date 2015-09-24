<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<html>
<head>
<title>Account Registration</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1 align="center">Register Your Account</h1>
	<div>
		<sf:form method="POST" commandName="Users">
			<sf:errors path="*" element="div" cssClass="errors" />
			<table align="center">
				<tr>
					<td><sf:label path="fname" cssErrorClass="error">First Name</sf:label>:
					</td>
					<td><sf:input path="fname" cssErrorClass="error" /></td>
				</tr>
				<tr>
					<td><sf:label path="lname" cssErrorClass="error">Last Name</sf:label>:
					</td>
					<td><sf:input path="lname" cssErrorClass="error" /></td>
				</tr>
				<tr>
					<td><sf:label path="email" cssErrorClass="error">Email</sf:label>:
					</td>
					<td><sf:input path="email" cssErrorClass="error" /></td>
				</tr>
				<tr>
					<td><sf:label path="passwd" cssErrorClass="error">Password</sf:label>:
					</td>
					<td><sf:input type="password" id="passwd" path = "passwd" cssErrorClass="error" /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Register" /></td>
				</tr>
			</table>
		</sf:form>
	</div>
</body>
</html>