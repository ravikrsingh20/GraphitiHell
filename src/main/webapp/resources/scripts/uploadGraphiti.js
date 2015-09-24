/**
 * 
 */
function uploadGraphiti() {
var canvas = document.getElementById("myCanvas");
var imageData=  canvas.toDataURL("image/jpeg");
var dataToPost = {image:imageData, latitude:lat,longitude:lng}
$.ajax({
  type: "POST",
  url: "http://localhost:8080/vg/upload/graphiti",
  data: dataToPost,
  success: function(data) {
    if(data.status == 'OK') alert('Works fine');
    else alert('Graphiti Successfully added to database');
  },error: function (jqXHR, textStatus, errorThrown) {
      alert(jqXHR + " : " + textStatus + " : " + errorThrown);
  }
});
}

function uploadLocation() {
	var canvas = document.getElementById("myCanvas");
	var lat = "55.555" ; 
	var lng = "55.555" ;//TODO:decide how to find out the location
	var imageData=  canvas.toDataURL("image/jpeg");
	var dataToPost = {image:imageData, latitude:lat,longitude:lng}
	$.ajax({
	  type: "POST",
	  url: "http://localhost:8080/vg/upload/location",
	  data: dataToPost,
	  success: function(data) {
	    if(data.status == 'OK') alert('Works fine');
	    else alert('successfully added Location to Graphiti');
	  },error: function (jqXHR, textStatus, errorThrown) {
	      alert(jqXHR + " : " + textStatus + " : " + errorThrown);
	  }
	});
	}