package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.GoodDaoImpl;
import com.web.shop.daoImpl.UserDaoImpl;
import com.web.shop.daoImpl.UserManagerDaoImpl;
import com.web.shop.model.User;

@Service
public class UserManagerService {
	@Autowired
	UserManagerDaoImpl daoImpl;
	@Autowired
	GoodDaoImpl goodDaoImpl;
	@Autowired
	UserDaoImpl userDaoImpl;

	@Transactional
	public boolean delUser(int userId) {
		userDaoImpl.delUserLeaveMessage(userId);
		boolean flag = goodDaoImpl.delGoodsByUserId(userId);
		if (flag) {
			boolean delUserById = daoImpl.delUserById(userId);
			return delUserById;
		}
		return flag;
	}

	@Transactional
	public ArrayList<User> getUser(int queryCount, int page, int type) {
		ArrayList<User> list = daoImpl.getUserList(queryCount, page, type);
		return list;
	}

	@Transactional
	public int getUserNum(int type) {
		int userCount = daoImpl.getUserCount(type);
		return userCount;
	}

	@Transactional
	public boolean setVerify(int id) {
		boolean b = daoImpl.setVerify(id);
		return b;
	}

	@Transactional
	public boolean setManager(int id) {
		boolean setManager = daoImpl.setManager(id);
		return setManager;
	}

	@Transactional
	public boolean setDined(int userId) {
		boolean userDined = daoImpl.setUserDined(userId);
		return userDined;
	}

	@Transactional
	public String resetUserPassword(int userId) {
		String password = daoImpl.resetUserPassword(userId);
		return password;
	}

	@Transactional
	public boolean isAdminExsit() {
		boolean b = daoImpl.isAdminExsit();
		return b;
	}
}
