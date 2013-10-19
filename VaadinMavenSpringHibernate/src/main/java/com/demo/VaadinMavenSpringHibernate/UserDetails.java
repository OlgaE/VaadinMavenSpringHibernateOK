package com.demo.VaadinMavenSpringHibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "New_User_Details")  // Now Hibernate knows that this class needs to be saved.
public class UserDetails {

	@Id  // The value of this field will be the primary key.
	@Column (name = "NewId")
	private int userId;
	
	@Column (name = "NewUserName")
	private String userName;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
