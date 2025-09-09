package com.edu.uptc.PersistenceWorkshop.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 7758888672641333914L;

	private String userName;

	private String password;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
