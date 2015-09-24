<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/VGStyle.css" />">
<script src="http://js.leapmotion.com/leap-0.6.3.min.js"></script>
<script src="https://js.leapmotion.com/leap-plugins-0.1.6.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<sec:authorize var="loggedIn" access="isAuthenticated()" />
<div align="center">
	<table align="center">
		<tr>
			<c:choose>
				<c:when test="${loggedIn}">
					<td><li><a href="/vg/logout">Log Out</a></li></td>
					<td><li><a href="/vg/drawGraphiti">Draw Graphiti</a></li></td>
					<td><li><a href="/vg/viewGraphiti">View Your
								Graphitis</a></li></td>
				</c:when>
				<c:otherwise>
					<td><li><a href="/vg/register">Register</a></li></td>
					<td><li><a href="/vg/login">Log In</a></li></td>
				</c:otherwise>
			</c:choose>
		</tr>

	</table>
	<ul>


	</ul>
</div>
