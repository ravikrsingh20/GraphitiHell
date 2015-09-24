package org.fit.vg.util;

import java.util.ArrayList;
import java.util.List;
/**
 * util class to store utility functions
 * @author Ravi Kumar Singh
 *
 */
public class UtilClass {
	/**
	 * utility function to convert a given radius to offset latitude and longitude which needs to be added to latitude and longitude asked
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public static List<Double> radius2latlng(double lat, double lng, double radius){
		//Latitude: 1 deg = 110574 m
		//1m = 1/110574
		//Longitude: 1 deg = 111320*cos(latitude) m
		//1m = 1/111320*cos(lat)
		List<Double> latlng = new ArrayList<Double>();
		double offsetLat = radius/(2 * 110574);
		double offsetLng = radius/(2*111320*Math.cos(lat));
		latlng.add(offsetLat);
		latlng.add(offsetLng);
		return latlng;
	}

}
