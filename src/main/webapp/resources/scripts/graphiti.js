/**
 * 
 */
// Store frame for motion functions
var previousFrame = null;

// Setup Leap loop with frame callback function
var controllerOptions = {enableGestures: true};

var fingerTypes = {
		THUMB:0,
		INDEX:1,
		MIDDLE:2,
		RING:3,
		PINKY:4		
}


var canvas = document.getElementById('GraphitiCanvas');
var ctx = canvas.getContext('2d');

var enablepaint = false;

var handPos = {x: 0, y: 0};
var coord = document.getElementById("coord");

var colorsList = ["#FF0000", //RED
                  "#FFA500", //ORANGE
                  "#00FF00", //GREEN
                  "#800080", //PURPLE
                  "#8B4513", //SADDLEBROWN
                  "#FF00FF", //MAGENTA
                  "#0000FF" //BLUE
                  ];
var colorindex = 0;

var canvasBackup = document.createElement('canvas');
canvasBackup.width = canvas.width;
canvasBackup.height = canvas.height;
var backCtx;
var thumbsUp = false;
Leap.loop(controllerOptions, function(frame) {
	/*If the previous frame was without spraying enabled, then clear the image*/
	enablepaint = false;
	ctx.clearRect(0,0,canvas.width,canvas.height);
	if(backCtx)
		ctx.drawImage(canvasBackup, 0,0);
	
	var interactionBox = frame.interactionBox;
	
	
	if (frame.hands.length > 0) {
	    var hand = frame.hands[0];
		handPos = mapHandPosToCanvas(interactionBox,hand,canvas);
		/*coord.innerHTML = "canvassize ("+ canvas_width+","+canvas_height+")("+  handPos.x + " "+ handPos.y
		+")\n("+interactionBox.normalizePoint(hand.palmPosition)[0]+","
		+interactionBox.normalizePoint(hand.palmPosition)[1] +"hehe";*/
		var fingers = frame.fingers;
		
		var thickness = 0;
		var thumbEnabled = false;
		var leftmost;
		var rightmost;
		var minX;
		var maxX;
		
		for (var i = 0; i < frame.pointables.length; i++) {
			var pointable = frame.pointables[i];
			
			if((pointable.type == fingerTypes.INDEX ||
					pointable.type == fingerTypes.MIDDLE||
					pointable.type == fingerTypes.RING||
					pointable.type == fingerTypes.PINKY)
					&& pointable.extended == true){
				if(leftmost == undefined){
					minX = pointable.stabilizedTipPosition[0];
					maxX = pointable.stabilizedTipPosition[0];
					leftmost = pointable;
					rightmost = pointable;
					
				}else if(pointable.stabilizedTipPosition[0] < minX){
					minX = pointable.stabilizedTipPosition[0];
					leftmost = pointable;
				}else if(pointable.stabilizedTipPosition[0] > maxX){
					maxX = pointable.stabilizedTipPosition[0];
					rightmost = pointable;
				}
				enablepaint = true;
			}else if (pointable.type == fingerTypes.THUMB &&
					pointable.extended == true){
				if(thumbsUp == false){
					colorindex = (colorindex+1)% colorsList.length;
					thumbsUp = true;
				}
				thumbEnabled = true;
			}
			
		}
		
		if(thumbEnabled == false){
			thumbsUp = false;
		}
		
		if(enablepaint == true){
			thickness = (maxX - minX)/10+1;
			ctx.fillStyle = colorsList[colorindex];
			ctx.fillRect(handPos.x,handPos.y,thickness,thickness);

			//Take a backup of this canvas
			if(!backCtx){
				backCtx = canvasBackup.getContext('2d');
			}
			backCtx.drawImage(canvas, 0,0);
		}else{
			thickness = 2;
			//draw a new frame
			ctx.fillStyle = colorsList[colorindex];;
			ctx.fillRect(handPos.x,handPos.y,thickness,thickness);
			
			//ctx.moveTo(handPos.x, handPos.y);
		}
		
		
		prevpos = handPos;
		
	}
	

  // Store frame for motion functions
  previousFrame = frame;
  
  //check gestures
  if(frame.valid && frame.gestures.length > 0){
	    frame.gestures.forEach(function(gesture){
	        switch (gesture.type){
	          case "circle":
	              console.log("Circle Gesture");
	              break;
	          case "keyTap":
	              console.log("Key Tap Gesture");
	              break;
	          case "screenTap":
	              console.log("Screen Tap Gesture");
	              break;
	          case "swipe":
	              console.log("Swipe Gesture");
	              saveGraphiti();
	              break;
	        }
	    });
	  }
})

function vectorToString(vector, digits) {
  if (typeof digits === "undefined") {
    digits = 1;
  }
  return "(" + vector[0].toFixed(digits) + ", "
             + vector[1].toFixed(digits) + ", "
             + vector[2].toFixed(digits) + ")";
}

function mapHandPosToCanvas(interactionBox,hand,canvas){
	var canvasPos = {x:0,y:0}
	var normalizedCoord = interactionBox.normalizePoint(hand.stabilizedPalmPosition);
	canvasPos.x = canvas.width * normalizedCoord[0];
	canvasPos.y = canvas.height * (1-normalizedCoord[1]);
	return canvasPos;
}

function saveGraphiti(){
	var yes = confirm("Do you want to save Graphiti.");
	if (yes == true) {
	   uploadGraphiti();
	} else {
	   return;
	}
}


function uploadGraphiti() {
	var canvas = document.getElementById("GraphitiCanvas");
	var imageData=  canvas.toDataURL("image/jpeg");
	var dataToPost = {image:imageData}
	$.ajax({
	  type: "POST",
	  url: "http://localhost:8080/vg/upload/graphiti",
	  data: dataToPost,
	  success: function(data) {
		  alert('Graphiti Successfully added to database');
		  window.location.href = "http://localhost:8080/vg/location";
	  },error: function (jqXHR, textStatus, errorThrown) {
	      alert(jqXHR + " : " + textStatus + " : " + errorThrown);
	  }
	});
	}

