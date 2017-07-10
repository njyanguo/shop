package com.shop.yi.login.dao.impl;

import org.springframework.stereotype.Repository;

import com.shop.yi.common.dao.ibatis.CommonDAO;
import com.shop.yi.login.dao.LoginDAO;
import com.shop.yi.login.dto.UserDTO;

@Repository("loginDAO")
public class LoginDAOImpl extends CommonDAO implements LoginDAO {


	@Override
	public UserDTO loginAuthDAO(String userId) {
		String sql = "sqlmap.sqlmap-login.loadUserInfoDetail";
		return (UserDTO) this.queryForObject(sql, userId);
	}

}
