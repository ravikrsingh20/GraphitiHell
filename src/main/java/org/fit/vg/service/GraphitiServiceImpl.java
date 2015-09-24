package org.fit.vg.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.fit.vg.dao.GraphitiDao;
import org.fit.vg.dao.UserDao;
import org.fit.vg.model.Images;
import org.fit.vg.model.Users;
import org.fit.vg.util.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/**
 * implementation of GraphitiService interface 
 * business logic for virtual graphiti application
 * @author Ravi Kumar Singh
 *
 */
@Service
@Transactional
public class GraphitiServiceImpl implements GraphitiService {
	@Autowired
	GraphitiDao graphitiDao;	
	@Autowired
	UserDao userDao;
	@Override
	public Images createGraphiti(Images graphiti) {
		// TODO Auto-generated method stub
		graphitiDao.create(graphiti);
		return graphiti; 
	}


	@Override
	public Images updateGraphitiLoc(Images graphiti) {
		// TODO Auto-generated method stub
		Images image = new Images();
		//the below method returns offset latitude and longitude for a particular radius
		List<Double> offsetCords = UtilClass.radius2latlng(graphiti.getLatitude(), graphiti.getLongitude(), 20);
		double offsetlat = offsetCords.get(0).doubleValue();
		double offsetlng = offsetCords.get(1).doubleValue();
		List<Images> imageList = graphitiDao.getGraphitiByradius(graphiti.getLatitude()-offsetlat, graphiti.getLatitude()+offsetlat, graphiti.getLongitude()-offsetlng, graphiti.getLongitude()+offsetlng);
		//graphitis exists within radius of 20m
		if(imageList !=null && imageList.size()>0){
			graphiti.setId(0);
			return graphiti;
		}else{
			image = graphitiDao.get(graphiti.getId());
			image.setImageName(graphiti.getImageName());
			image.setLatitude(graphiti.getLatitude());
			image.setLongitude(graphiti.getLongitude());
			graphitiDao.update(image);
			return image;			
		}
		
	}

	@Override
	public Images getGraphiti(Images graphiti) {
		// TODO Auto-generated method stub
		return graphitiDao.get(graphiti.getId());

	}

	@Override
	public Users createUserAccount(Users users) {
		// TODO Auto-generated method stub
		users.setEnabled(true);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		users.setPasswd(passwordEncoder.encode(users.getPasswd())); 
		userDao.create(users);
		return users;
	}

	@Override
	public List<Images> getGraphiti(double lat, double lng , double radius) {
		// TODO Auto-generated method stub
		Images image = new Images();
		
		//the below method returns offset latitude and longitude for a particular radius
		List<Double> offsetCords = UtilClass.radius2latlng(lat, lng, radius);
		double offsetlat = offsetCords.get(0).doubleValue();
		double offsetlng = offsetCords.get(1).doubleValue();
		List<Images> imageList =  graphitiDao.getGraphitiByradius(lat - offsetlat, lat + offsetlat, lng - offsetlng, lng + offsetlng);
		List<Images> newList = new LinkedList<Images>();
		for(Images img : imageList)
		{
			Images newImages = new Images();
			newImages.setId(img.getId());
			newImages.setLatitude(img.getLatitude());
			newImages.setLongitude(img.getLongitude());
			newList.add(newImages);
		}
		return newList;
	}
	@Override
	public List<Images> viewGraphitiMobile(double lat, double lng , double radius) {
		// TODO Auto-generated method stub
		Images image = new Images();
		
		//the below method returns offset latitude and longitude for a particular radius
		List<Double> offsetCords = UtilClass.radius2latlng(lat, lng, radius);
		double offsetlat = offsetCords.get(0).doubleValue();
		double offsetlng = offsetCords.get(1).doubleValue();
		List<Images> imageList = graphitiDao.getGraphitiByradius(lat - offsetlat, lat + offsetlat, lng - offsetlng, lng + offsetlng);		
		return imageList;
	}
	@Override
	public List<Images> getGraphitiByUser(Images graphiti) {
		// TODO Auto-generated method stub
		List<Images> imageListret= new ArrayList<Images>();
		List<Images> imageList=  graphitiDao.getGraphitiByUser(graphiti);
		return imageList;
	}


	@Override
	public Images getGraphitiByUserIdAndImageId(Images graphiti) {
		// TODO Auto-generated method stub
		return graphitiDao.getGraphitiByUserIdImageId(graphiti);
	}




}
