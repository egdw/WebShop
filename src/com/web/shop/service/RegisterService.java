package com.web.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.RegisterDaoImpl;
import com.web.shop.model.User;

@Service
public class RegisterService {
	@Autowired
	private RegisterDaoImpl daoImpl;

	@Transactional
	public boolean register(User user, String sid) {
		boolean register = daoImpl.register(user, sid);
		return register;
	}
}
