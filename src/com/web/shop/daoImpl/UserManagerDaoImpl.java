package com.web.shop.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.web.shop.dao.UserManagerDao;
import com.web.shop.model.User;
import com.web.shop.util.RandomUtils;
import com.web.shop.util.UserUtils;

@Component
public class UserManagerDaoImpl implements UserManagerDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<User> getUserList(int queryCount, int page, int type) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = null;
		if (type == 0) {
			createSQLQuery = session
					.createSQLQuery("select * from USER limit ?,?");
			createSQLQuery.setInteger(0, queryCount * page);
			createSQLQuery.setInteger(1, queryCount);
		} else if (type == 1) {
			createSQLQuery = session
					.createSQLQuery("select * from USER where MANAGER = ? limit ?,?");
			createSQLQuery.setInteger(0, 0);
			createSQLQuery.setInteger(1, queryCount * page);
			createSQLQuery.setInteger(2, queryCount);
		} else if (type == 2) {
			createSQLQuery = session
					.createSQLQuery("select * from USER where MANAGER = ? limit ?,?");
			createSQLQuery.setInteger(0, 1);
			createSQLQuery.setInteger(1, queryCount * page);
			createSQLQuery.setInteger(2, queryCount);
		} else if (type == 3) {
			createSQLQuery = session
					.createSQLQuery("select * from USER where VERIFY =? ");
			createSQLQuery.setInteger(0, 0);
		} else {
			return null;
		}
		createSQLQuery.addEntity(User.class);
		ArrayList<User> list = (ArrayList<User>) createSQLQuery.list();
		return list;
	}

	@Override
	public boolean setUserDined(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userId);
		User currentUser = UserUtils.getCurrentUser();
		if (currentUser != null) {
			if (currentUser.getUser_id() == userId) {
				return false;
			}
		}
		if (user != null) {
			user.setDenied(!user.isDenied());
			session.update(user);
			session.flush();
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserById(int userId) {
		if (isManager()) {
			Session session = sessionFactory.getCurrentSession();
			User user = (User) session.get(User.class, userId);
			if (user != null) {
				session.delete(user);
				session.flush();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isManager() {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null && user.isManager()) {
			return true;
		}
		return false;
	}

	@Override
	public int getUserCount(int type) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = null;
		if (type == 0) {
			createSQLQuery = session
					.createSQLQuery("select count(*) from USER");
		} else if (type == 1) {
			createSQLQuery = session
					.createSQLQuery("select count(*) from USER where MANAGER =? ");
			createSQLQuery.setInteger(0, 0);
		} else if (type == 2) {
			createSQLQuery = session
					.createSQLQuery("select count(*) from USER where MANAGER =? ");
			createSQLQuery.setInteger(0, 1);
		} else if (type == 3) {
			createSQLQuery = session
					.createSQLQuery("select count(*) from USER where VERIFY =? ");
			createSQLQuery.setInteger(0, 0);
		} else {
			return 0;
		}
		int count = ((Number) createSQLQuery.uniqueResult()).intValue();
		return count;
	}

	@Override
	public boolean setVerify(int id) {
		Session session = sessionFactory.getCurrentSession();
		User object = (User) session.get(User.class, id);
		if (object != null) {
			object.setVerify(true);
			session.update(object);
			session.flush();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean setManager(int id) {
		Session session = sessionFactory.getCurrentSession();
		User object = (User) session.get(User.class, id);
		User currentUser = UserUtils.getCurrentUser();
		if (currentUser != null && object != null) {
			Integer user_id = currentUser.getUser_id();
			if (user_id == object.getUser_id()) {
				return false;
			}
		} else {
			return false;
		}
		object.setManager(!object.isManager());
		session.update(object);
		session.flush();
		return true;
	}

	@Override
	public String resetUserPassword(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String randomPassword = RandomUtils.getRandomPassword();
		User user = (User) session.get(User.class, userId);
		if (user != null && !user.isManager()) {
			user.setUser_password(randomPassword);
			return randomPassword;
		}
		return null;
	}

	@Override
	public boolean isAdminExsit() {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select * from USER where USER_USERNAME = admin");
		query.addEntity(User.class);
		List list = query.list();
		if (list.size() > 0) {
			return true;
		}
		return false;
	}
}
