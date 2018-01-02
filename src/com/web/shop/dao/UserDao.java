package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.Good;
import com.web.shop.model.User;
import com.web.shop.model.UserLeaveMessage;

@Repository
public interface UserDao {
	public User getUserById(int id);

	public void publishUserLeaveMessage(Good good, User user, String context);

	public ArrayList<UserLeaveMessage> getUserLeaveMessages(int goodId);

	public User getUserByUsernameAndPassword(String username, String passwordf);

	public void delUserLeaveMessage(long userId);

	public boolean updatePassword(String oldPassword, String newPassword);

}
