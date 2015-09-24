package org.fit.vg.dao;

import java.util.List;

import org.fit.vg.model.Images;

/**
 * graphiti Dao interface for accessing DB
 * consists of hibernate queries
 * @author Ravi Kumar Singh
 *
 */
public interface GraphitiDao extends Dao<Images>{
	/**
	 * gets graphiti from db for given latitude and longitude 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public Images getGraphitiByloc(double lat, double lng);
	
	/**
	 * gets list of graphiti from db within the start and end latitude and longitude specified in the argument
	 * @param startlat
	 * @param endlat
	 * @param startlng
	 * @param endlng
	 * @return
	 */
	public List<Images> getGraphitiByradius(double startlat,double endlat,double startlng, double endlng);
	/**
	 * gets list of graphitis from DB for a particular logged in user
	 * @param images
	 * @return
	 */
	public List<Images> getGraphitiByUser(Images images);
	/**
	 * gets a graphiti for a particular user and graphiti id
	 * @param images
	 * @return
	 */
	public Images getGraphitiByUserIdImageId(Images images);
     
	
}
