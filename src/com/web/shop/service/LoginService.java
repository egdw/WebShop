package com.web.shop.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.LoginDaoImpl;
import com.web.shop.daoImpl.UserDaoImpl;
import com.web.shop.model.User;

@Service
public class LoginService {
	@Autowired
	private LoginDaoImpl daoImpl;
	@Autowired
	private UserDaoImpl daoImpl2;

	@Transactional
	public boolean loginAccount(User user) {
		boolean login = daoImpl.login(user);
		return login;
	}

	@Transactional
	public User getUserByUsernameAndPassword(String username, String password) {
		User userByUsernameAndPassword = daoImpl2.getUserByUsernameAndPassword(
				username, password);
		return userByUsernameAndPassword;
	}
}
