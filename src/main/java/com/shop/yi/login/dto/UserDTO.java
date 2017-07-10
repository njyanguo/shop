package com.shop.yi.login.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6416883468126374021L;

	private String userId;
	
	private String userName;
	private String password;
	
	private String phone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
