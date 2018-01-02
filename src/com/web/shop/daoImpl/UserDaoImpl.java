package com.web.shop.daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.UserDao;
import com.web.shop.model.Good;
import com.web.shop.model.User;
import com.web.shop.model.UserLeaveMessage;
import com.web.shop.util.SessionFactoryUtils;
import com.web.shop.util.UserUtils;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User object = (User) session.get(User.class, id);
		return object;
	}

	@Override
	public void publishUserLeaveMessage(Good good, User user, String context) {
		Session session = sessionFactory.getCurrentSession();
		UserLeaveMessage userLeaveMessage = new UserLeaveMessage();
		userLeaveMessage.setContext(context);
		userLeaveMessage.setCreateDate(new Date(System.currentTimeMillis()));
		userLeaveMessage.setGood(good);
		userLeaveMessage.setUser(user);
		session.save(userLeaveMessage);
		session.flush();
	}

	@Override
	public ArrayList<UserLeaveMessage> getUserLeaveMessages(int goodId) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select * from USERLEAVEMESSAGE where GOOD = ?");
		query.setInteger(0, goodId);
		query.addEntity(UserLeaveMessage.class);
		ArrayList<UserLeaveMessage> list = (ArrayList<UserLeaveMessage>) query
				.list();
		return list;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session
				.createSQLQuery("select * from USER where USER_USERNAME = ? and USER_PASSWORD = ?");
		sqlQuery.setString(0, username);
		sqlQuery.setString(1, password);
		sqlQuery.addEntity(User.class);
		List<User> list = sqlQuery.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void delUserLeaveMessage(long userId) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("delete from USERLEAVEMESSAGE where USER = ?");
		query.setLong(0, userId);
		query.executeUpdate();
	}

	@Override
	public boolean updatePassword(String oldPassword, String newPassword) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, UserUtils.getCurrentUser()
				.getUser_id());
		String password = user.getUser_password();
		if (password.equals(oldPassword)) {
			user.setUser_password(newPassword);
			session.flush();
			return true;
		}
		return false;
	}

}
