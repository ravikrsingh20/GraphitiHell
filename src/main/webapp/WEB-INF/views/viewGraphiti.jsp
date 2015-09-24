<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Graphiti</title>
</head>
<body>
<%@ include file="header.jsp"%>
	<h1 align="center">Your Graphiti</h1>
	<div align="center">
		<table align="center">
			<tr>
				<td>Graphiti Id</td>
				<td>Latitude</td>
				<td>Longitude</td>
			</tr>
				<tr>
					<td><c:out value="${Images.id}" /></td>
					<td><c:out value="${Images.latitude}" /></td>
					<td><c:out value="${Images.longitude}" /></td>
				</tr>
		</table>
		<img src="data:image/jpeg;base64,${Images.imageName}" width="200" height="200">
	</div>

</body>
</html>