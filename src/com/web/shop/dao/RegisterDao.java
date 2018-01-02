package com.web.shop.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.web.shop.model.User;

@Repository
public interface RegisterDao {
	// register user
	public boolean register(User user,String sid);
}
