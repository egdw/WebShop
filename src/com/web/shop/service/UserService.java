package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.web.shop.daoImpl.GoodDaoImpl;
import com.web.shop.daoImpl.UserDaoImpl;
import com.web.shop.model.Good;
import com.web.shop.model.User;
import com.web.shop.model.UserLeaveMessage;
import com.web.shop.util.UserUtils;

@Service
public class UserService {

	@Autowired
	private UserDaoImpl daoImpl;
	@Autowired
	private GoodDaoImpl goodDaoImpl;

	@Transactional
	public User getUserById(int userId) {
		return daoImpl.getUserById(userId);
	}

	@Transactional
	public boolean savePublishLeaveMessage(int goodId, String context) {
		User user = UserUtils.getCurrentUser();
		if (user != null) {
			Good goodById = goodDaoImpl.getGoodById(goodId);
			if (goodById != null && context != null && !context.isEmpty()) {
				daoImpl.publishUserLeaveMessage(goodById, user, context);
				return true;
			}
		}
		return false;
	}

	@Transactional
	public ArrayList<UserLeaveMessage> getUserLeaveMessages(int goodId) {
		ArrayList<UserLeaveMessage> userLeaveMessages = daoImpl
				.getUserLeaveMessages(goodId);
		return userLeaveMessages;
	}

	@Transactional
	public boolean updatePassword(String oldPassword, String newPassword) {
		boolean password = daoImpl.updatePassword(oldPassword, newPassword);
		return password;
	}

}
