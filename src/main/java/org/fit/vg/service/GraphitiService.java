package org.fit.vg.service;

import java.util.List;

import org.fit.vg.model.Images;
import org.fit.vg.model.Users;
/**
 * 
 * @author Ravi Kumar Singh
 * Service interface to provide services related to virtual graphitis
 *
 */
public interface GraphitiService {
	/**
	 * creates graphiti in DB
	 * @param graphiti
	 * @return
	 */
	public Images createGraphiti(Images graphiti);
	/**
	 * gets graphiti from DB based on graphiti Id
	 * @param graphiti
	 * @return
	 */
	public Images getGraphiti(Images graphiti);
	/**
	 * gets graphiti from DB based on latitude,longitude and a specified radius without the image payload
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public List<Images>  getGraphiti(double lat, double lng,double radius);
	/**
	 * gets graphitis from DB based on latitude, longitude and a specified radius
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public List<Images> viewGraphitiMobile(double lat, double lng , double radius);
	/**
	 * creates a user account for user registering to application
	 * @param users
	 * @return
	 */
	public Users createUserAccount(Users users);
	/**
	 * updates location for the graphiti using its graphiti id
	 * @param graphiti
	 * @return
	 */
	public Images updateGraphitiLoc(Images graphiti);
	/**
	 * gets graphitis from db for a logged in user
	 * @param graphiti
	 * @return
	 */
	public List<Images> getGraphitiByUser(Images graphiti);
	/**
	 * gets graphitis from db by userid and Image id
	 * @param graphiti
	 * @return
	 */
	public Images getGraphitiByUserIdAndImageId(Images graphiti);
}
