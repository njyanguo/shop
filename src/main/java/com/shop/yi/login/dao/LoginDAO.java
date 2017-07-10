package com.shop.yi.login.dao;

import com.shop.yi.login.dto.UserDTO;

/**
 * 
 * @author 国
 *
 */
public interface LoginDAO {
	
	public UserDTO loginAuthDAO(String userId);

}
