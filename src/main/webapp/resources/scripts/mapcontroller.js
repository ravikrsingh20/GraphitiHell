/**
 * Initial part of the code is forked from 
 * https://github.com/jaxzin/leap-map
 * 
 * Thanks to Brian R. Jackson (jaxzin)
 */

//Store frame for motion functions
var previousFrame = null;
var map;
var leftHandPrev;
var separationStart;

//Setup Leap loop with frame callback function
var controllerOptions = {enableGestures: true};

var MAX_ZOOM = 22;
var SEPARATION_SCALING = 1.25;
var LEFT_HAND = 0, RIGHT_HAND = 1;
var X = 0, Y = 1, Z = 2;
Leap.loop(controllerOptions, function(frame) {
	moveforMap(frame);

});
function moveforMap(frame) {
	
	// Look for any circle gestures and process the zoom
	// TODO: filter out multiple circle gestures per frame
	if (frame.valid && frame.gestures.length > 0) {
		frame.gestures.forEach(function(gesture) {
			filterGesture("circle", zoom)(frame, gesture);
			filterGesture("screenTap", screenTap)(frame, gesture);
		});
	}

	markHands(frame);

	// if there is one hand grabbing...
	if (frame.hands.length > 0 && isGripped(frame.hands[LEFT_HAND])) {
		var leftHand = frame.hands[LEFT_HAND];
		var rightHand = frame.hands.length > 1 ? frame.hands[RIGHT_HAND]
				: undefined;
		var separation;

		// If there was no previous closed position, capture it and exit
		if (leftHandPrev == null) {
			leftHandPrev = leftHand;
			return;
		}

		// if there is a right hand and its gripped...
		if (rightHand) {
			if (isGripped(rightHand)) {
				separation = Math.sqrt(Math.pow(
						rightHand.stabilizedPalmPosition[X]
								- leftHand.stabilizedPalmPosition[X], 2)
						+ Math.pow(rightHand.stabilizedPalmPosition[Y]
								- leftHand.stabilizedPalmPosition[Y], 2));
				// console.log("separation = " + separation + " ("+separationStart+")");

				// ...and no previous separation, capture and exit
				if (separationStart == null) {
					separationStart = separation;
					return;
				}

				// Calculate if we need to change the zoom level
				var currentZoom = map.getZoom();

				if (currentZoom > 1
						&& separation < (separationStart / SEPARATION_SCALING)) {
					map.setZoom(currentZoom - 1);
					separationStart = separation;
				} else if (currentZoom < MAX_ZOOM
						&& separation > (SEPARATION_SCALING * separationStart)) {
					map.setZoom(currentZoom + 1);
					separationStart = separation;
				}
				// If the right hand is not gripped...
			} else if (separationStart != null) {
				separationStart = null;
			}

		}

		// Calculate how much the hand moved
		var dX = leftHandPrev.stabilizedPalmPosition[X]
				- leftHand.stabilizedPalmPosition[X];
		var dY = leftHandPrev.stabilizedPalmPosition[Y]
				- leftHand.stabilizedPalmPosition[Y];
		// console.log("Movement: " + dX + ","+dY);

		var center = map.getCenter();

		var scaling = 4.0 / Math.pow(2, map.getZoom() - 1);

		var newLat = center.lat() + dY * scaling;
		var newLng = center.lng() + dX * scaling;

		var newCenter = new google.maps.LatLng(newLat, newLng);

		// console.log(newCenter)

		map.setCenter(newCenter);

		leftHandPrev = leftHand;

	} else {
		// If the left hand is not in a grab position, clear the last hand position
		if (frame.hands.length > LEFT_HAND
				&& !isGripped(frame.hands[LEFT_HAND]) && leftHandPrev != null) {
			leftHandPrev = null;
		}

		// if the right hand is not in a grab position, clear the separation
		if (frame.hands.length > RIGHT_HAND
				&& !isGripped(frame.hands[RIGHT_HAND])
				&& separationStart != null) {
			separationStart = null;
		}
		// console.log("Clearing lastHand");
	}
}

var handMarkers = [];

var HEIGHT_OFFSET = 150;
var BASE_MARKER_SIZE_GRIPPED = 350000, BASE_MARKER_SIZE_UNGRIPPED = 500000;

function markHands(frame) {
	var scaling = (4.0 / Math.pow(2, map.getZoom() - 1));

	var bounds = map.getBounds();
	// FIXME: Sometimes this gets run too early, just exit if its too early.
	if (!bounds) {
		return;
	}
	var origin = new google.maps.LatLng(bounds.getSouthWest().lat(), bounds
			.getCenter().lng());

	var hands = frame.hands;
	for ( var i in hands) {
		if (hands.hasOwnProperty(i)) {

			// Limit this to 2 hands for now
			if (i > RIGHT_HAND) {
				return;
			}

			var hand = hands[i];

			newCenter = new google.maps.LatLng(
					origin.lat()
							+ ((hand.stabilizedPalmPosition[1] - HEIGHT_OFFSET) * scaling),
					origin.lng() + (hand.stabilizedPalmPosition[0] * scaling));

			// console.log(center.lat() + "," + center.lng());
			// console.log(newCenter.lat() + "," + newCenter.lng());

			var gripped = isGripped(hand);
			var baseRadius = gripped ? BASE_MARKER_SIZE_GRIPPED
					: BASE_MARKER_SIZE_UNGRIPPED;

			var handColor = getHandColor(hand);

			var handMarker = handMarkers[i];
			if (!handMarker) {
				handMarker = new google.maps.Circle();
				handMarkers[i] = handMarker;
			}

			handMarker.setOptions({
				strokeColor : handColor,
				strokeOpacity : 0.8,
				strokeWeight : 2,
				fillColor : handColor,
				fillOpacity : 0.35,
				map : map,
				center : newCenter,
				radius : baseRadius * scaling
			});

		}
	}


}

var INDEX_FINGER = 1;
var locationMarker;

function screenTap(frame, keyTapGesture) {
	var handMarker;
	if (keyTapGesture.pointableIds.length == 1
			) {
		switch (keyTapGesture.state) {
		case "start":
			break;
		case "update":
			break;
		case "stop":
			//get the location of the hand 
			handMarker = handMarkers[0];
			
			
			if(handMarker){
					if(!locationMarker){
						locationMarker = new google.maps.Circle();
					}
					locationMarker.setOptions({
						strokeColor : "rgb(0,119,0)",
						strokeOpacity : 0.8,
						strokeWeight : 2,
						fillColor : "rgb(0,119,0)",
						fillOpacity : 0.8,
						map : map,
						center : handMarker.getCenter(),
						radius : BASE_MARKER_SIZE_GRIPPED * (4.0 / Math.pow(2, map.getZoom() - 1))
					});
					document.getElementById('coord').innerHTML = handMarker.getCenter();
					document.getElementById('coord').style.color = "black";
				
			}
			break;
			
		}
	}
}

var zoomLevelAtCircleStart;

function zoom(frame, circleGesture) {
	// Only zoom based on one index finger
	if (circleGesture.pointableIds.length == 1
			&& frame.pointable(circleGesture.pointableIds[0]).type == INDEX_FINGER) {
		switch (circleGesture.state) {
		case "start":
			zoomLevelAtCircleStart = map.getZoom();
			// fall through on purpose...
		case "update":
			// figure out if we need to change the zoom level;
			var zoomChange = Math.floor(circleGesture.progress);
			var currentZoom = map.getZoom();
			var zoomDirection = isClockwise(frame, circleGesture) ? zoomChange
					: -zoomChange;
			if (zoomLevelAtCircleStart + zoomDirection != currentZoom) {
				var newZoom = zoomLevelAtCircleStart + zoomDirection;
				if (newZoom >= 0 && newZoom <= MAX_ZOOM) {
					map.setZoom(newZoom);
				}
			}
			break;
		case "stop":
			zoomLevelAtCircleStart = null;
			break;
		}
	}
}

function initialize() {
	var mapOptions = {
		zoom : 8,
		center : new google.maps.LatLng(50.715861, 7.138543),
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		mapTypeControlOptions : {
			position : google.maps.ControlPosition.TOP_LEFT
		}
	};
	map = new google.maps.Map(document.getElementById('mapCanvas'), mapOptions);

	/*// listen to Leap Motion
	Leap.loop({
		enableGestures : true
	}, move);*/
}

// ==== utility functions =====

/** Returns the truth that a Leap Motion API Hand object is currently in a gripped or "grabbed" state.
 */
function isGripped(hand) {
	return hand.grabStrength == 1.0;
}

function getHandColor(hand) {
	if (isGripped(hand)) {
		return "rgb(0,119,0)";
	} else {
		var tint = Math.round((1.0 - hand.grabStrength) * 119);
		tint = "rgb(119," + tint + "," + tint + ")";
		return tint;
	}
}

function filterGesture(gestureType, callback) {
	return function(frame, gesture) {
		if (gesture.type == gestureType) {
			callback(frame, gesture);
		}
	}
}

function isClockwise(frame, gesture) {
	var clockwise = false;
	var pointableID = gesture.pointableIds[0];
	var direction = frame.pointable(pointableID).direction;
	var dotProduct = Leap.vec3.dot(direction, gesture.normal);

	if (dotProduct > 0)
		clockwise = true;

	return clockwise;
}

google.maps.event.addDomListener(window, 'load', initialize);