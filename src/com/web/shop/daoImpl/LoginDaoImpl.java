package com.web.shop.daoImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.LoginDao;
import com.web.shop.model.User;

@Component
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean login(User user) {
		Session openSession = sessionFactory.getCurrentSession();
		SQLQuery query = openSession
				.createSQLQuery("select * from USER where USER_USERNAME=?");
		query.setString(0, user.getUser_username());
		query.addEntity(User.class);
		List<User> list = query.list();
		if (list != null && list.size() > 0) {
			User object = list.get(0);
			if (object != null
					&& user != null
					&& object.getUser_username()
							.equals(user.getUser_username())
					&& object.getUser_password()
							.equals(user.getUser_password())) {
				return true;
			}
		}
		return false;
	}

}
