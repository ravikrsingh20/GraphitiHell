<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="true"%>
<html>
<head>
<title>Online Login</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1 align="center">Enter your details to log in</h1>
	<div align="center">
		<c:url value="/login" var="loginUrl" />
		<form action="${loginUrl}" method="post">
			<c:if test="${param.error != null}">
				<p>Invalid username and password.</p>
			</c:if>
			<c:if test="${param.logout != null}">
				<p>You have been logged out.</p>
			</c:if>
			<table align="center">
				<tr>
					<td><label for="username">UserName/Email id</label></td>
					<td><input type="text" id="username" name="username" /></td>
				</tr>
				<tr>
					<td><label for="password">Password</label></td>
					<td><input type="password" id="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="submit" class="btn">Log
							in</button></td>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</tr>
			</table>
		</form>
	</div>
</body>
</html>