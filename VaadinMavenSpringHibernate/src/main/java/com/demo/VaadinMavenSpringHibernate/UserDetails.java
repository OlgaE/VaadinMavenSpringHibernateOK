package com.demo.VaadinMavenSpringHibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity   // Now Hibernate knows that this class needs to be saved.
public class UserDetails {

	@Id  // The value of this field will be the primary key.
	private int userId;
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
