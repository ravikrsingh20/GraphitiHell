<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Graphiti List</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1 align="center">Your Graphiti List</h1>
	<div>
		<table align="center">
			<tr>
				<td>Graphiti Id</td>
				<td>Latitude</td>
				<td>Longitude</td>
				<td>View Graphiti</td>
			</tr>
			<c:forEach items="${ImageList}" var="images">
				<tr>
					<c:url var="viewImage" value="/graphiti?id=${images.id}" />
					<td><c:out value="${images.id}" /></td>
					<td><c:out value="${images.latitude}" /></td>
					<td><c:out value="${images.longitude}" /></td>
					<td><a href="${viewImage}">View Graphiti</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>