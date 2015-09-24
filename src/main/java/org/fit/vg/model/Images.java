package org.fit.vg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * model class to store image into database
 * @author Ravi Kumar Singh
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({ @NamedQuery(name = "getGraphitiByloc", query = "from Images g where g.latitude = :lat and g.longitude = :lng" )})
public class Images {
	@Id
	@GeneratedValue
	@Column
	private int id;
	@Column
	private double latitude;
	@Column
	private double longitude;
	@Column
	@Lob
	private byte[] image;
	@Column
	private String locname;
	@Column
	private String imageName;
	@Column
	private String userName;
	@Transient
	private double radius;
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
	
}
