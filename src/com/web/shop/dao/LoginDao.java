package com.web.shop.dao;

import org.springframework.stereotype.Repository;

import com.web.shop.model.User;
@Repository
public interface LoginDao {
	public boolean login(User user);
}
