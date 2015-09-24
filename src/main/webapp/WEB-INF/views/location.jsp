<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location Selection</title>
</head>
<body>
<%@ include file="header.jsp"%>	
<h2 align="center">Select location to save Graphiti id <label for="imageId"><c:out value="${sessionScope.ImageId} " /></label></h2>
<br>Location:
<div id="coord">Please select!!</div>
<div id="map-canvas"></div>
<script src="<c:url value="/resources/scripts/map.js" />"></script>
</body>
</html>