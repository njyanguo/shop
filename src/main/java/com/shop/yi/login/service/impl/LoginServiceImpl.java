package com.shop.yi.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.yi.login.dao.LoginDAO;
import com.shop.yi.login.dto.UserDTO;
import com.shop.yi.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name="loginDAO")
	private LoginDAO loginDao;
	@Override
	public void loginAuth(String userId, String password) {
		// TODO Auto-generated method stub
		UserDTO loginUserInfo = loginDao.loginAuthDAO(userId);
	}

		
}
