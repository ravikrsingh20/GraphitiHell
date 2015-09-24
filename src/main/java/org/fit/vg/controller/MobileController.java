package org.fit.vg.controller;

import java.util.List;

import org.fit.vg.model.Images;
import org.fit.vg.service.GraphitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * rest controller to handle rest calls from android app
 * @author Ravi Kumar Singh
 *
 */
@RestController
public class MobileController {
	@Autowired
	private GraphitiService graphitiService;
	public MobileController()
	{
		System.out.println("MobileController initiated");
	}
	
	/**
	 * returns list of graphitis detected in a specified radius
	 * @param image
	 * @return
	 */
	
	@RequestMapping(value = "/rest/detectGraphiti", method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
	public  ResponseEntity<List<Images>> detectGraphiti(@RequestBody Images image) {
		List <Images> imageList  = graphitiService.getGraphiti(image.getLatitude(), image.getLongitude(), image.getRadius());
		if (imageList != null && imageList.size()>0)
			return new  ResponseEntity<List<Images>>(imageList, HttpStatus.OK);
		else		
			return new  ResponseEntity<List<Images>>(imageList, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * will return list of graphitis with images in json response for the asked radius
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "/rest/viewGraphiti", method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
	public  ResponseEntity<List<Images>> viewGraphiti(@RequestBody Images image) {
		List <Images> imageList  = graphitiService.viewGraphitiMobile(image.getLatitude(), image.getLongitude(), image.getRadius());
		if (imageList != null && imageList.size()>0)
			return new  ResponseEntity<List<Images>>(imageList, HttpStatus.OK);
		else		
			return new  ResponseEntity<List<Images>>(imageList, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * dummy rest api to check the json message format
	 * @return
	 */
	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public  ResponseEntity<Images> jsonformat() {
		Images image = new Images();	
		image.setLatitude(45.4567);
		image.setLongitude(23.23456);
		image.setRadius(500);
		return new  ResponseEntity<Images>(image, HttpStatus.OK);
	}
}
