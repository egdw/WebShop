package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.User;

@Repository
public interface UserManagerDao {
	/**
	 * 获取相应的用于
	 * 
	 * @param queryCount
	 *            每次查询的数量
	 * @param page
	 *            当前查询的页数
	 * @return
	 */
	public ArrayList<User> getUserList(int queryCount, int page, int type);

	/**
	 * 修改登陆权限
	 * 
	 * @param flag
	 *            是否封禁
	 * @param userId
	 *            用户id
	 * @return
	 */
	public boolean setUserDined(int userId);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	public boolean delUserById(int userId);

	public boolean setVerify(int id);

	/**
	 * 
	 * @param id
	 * @param flag
	 *            0 false 1 true
	 * @return
	 */
	public boolean setManager(int id);

	public boolean isManager();

	public int getUserCount(int type);
	
	public String resetUserPassword(int userId);
	
	public boolean isAdminExsit();
}
