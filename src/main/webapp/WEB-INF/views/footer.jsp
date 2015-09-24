


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html ng-app="myGraphiti">
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/VGStyle.css" />">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script src="http://js.leapmotion.com/leap-0.6.3.min.js"></script>
<script src="<c:url value="/resources/scripts/pointer.js" />"></script>
<script src="<c:url value="/resources/scripts/GraphitiAppController.js" />"></script>
<script src="<c:url value="/resources/scripts/mapcontroller.js" />"></script>
<script src="<c:url value="/resources/scripts/angularApplication.js" />"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-animate.min.js"></script>
<script src="https://code.angularjs.org/1.3.0/angular-messages.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script src="https://js.leapmotion.com/leap-plugins-0.1.6.js"></script>
<script src="https://js.leapmotion.com/leap.rigged-hand-0.1.4.min.js"></script>
<title>Virtual Graphiti</title>
</head>
<body>

	<button id="nextorPrev" onclick="toggleNextOrPrevious()">Next</button>
	<br>Location:
	<div id="coord">Please select!!</div>

	<div id="paintCanvas">

		<div >
			<button id="saveButton" ng-click="save()">Save</button>
			<p id="storageStatus">{{saveStatus}}</p>
		</div>
		<div id="border">
			<canvas id="GraphitiCanvas"></canvas>
		</div>

	</div>

	<div id="map-canvas"></div>


	<script src="scripts/angularApplication.js"></script>
	<script src="scripts/GraphitiAppController.js"></script>
	<script src="scripts/pointer.js"></script>
	<script src="scripts/mapcontroller.js"></script>

</body>

</html>
