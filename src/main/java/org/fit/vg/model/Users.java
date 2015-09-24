package org.fit.vg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
/**
 * model class to store users into database
 * @author Ravi Kumar Singh
 *
 */
@Entity
public class Users {
	@Id
	@GeneratedValue
	@Column
	private int id;
	@Column
	private String uname;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String passwd;
	@Column
	@Email
	private String email;
	@Column
	private boolean enabled;
	@Transient
	private String cnfPasswd;
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getCnfPasswd() {
		return cnfPasswd;
	}
	public void setCnfPasswd(String cnfPasswd) {
		this.cnfPasswd = cnfPasswd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
