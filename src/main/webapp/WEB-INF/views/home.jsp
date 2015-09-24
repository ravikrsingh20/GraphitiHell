<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Virtual Graphiti</title>
</head>
<body>
	<%@ include file="header.jsp"%>	
	<h2 align="center">First Register/Login. Also Please note below gestures to be used</h2>
	
	<h2 align="center">Gestures used</h2>
	<table align="center">
		<tr>
			<td>Start graphiti</td>
			<td>Open your fist into to make a palm</td>
		</tr>
		<tr>
			<td>Stop graphiti</td>
			<td>Close Your palm to make a fist</td>
		</tr>
		<tr>
			<td>Paint graphiti</td>
			<td>Use your index/middle finger to paint</td>
		</tr>
		<tr>
			<td>Save graphiti</td>
			<td>Swipe your palm from left to right</td>
		</tr>
		<tr>
			<td>Zoom in map </td>
			<td>Clockwise circular gesture </td>
		</tr>
		<tr>
			<td>Zoom out of map</td>
			<td>anticlockwise circular gesture</td>
		</tr>
		<tr>
			<td>Select location</td>
			<td>screen tap gesture</td>
		</tr>
		<tr>
			<td>Save location</td>
			<td>Swipe your palm from left to right</td>
		</tr>
	</table>
	
<script src="<core:url value="/resources/scripts/graphiti.js" />"></script>
</body>
</html>