package com.web.shop.daoImpl;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.RegisterDao;
import com.web.shop.model.Student;
import com.web.shop.model.User;

@Component
public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private StudentDaoImpl studentDaoImpl;

	@Override
	public boolean register(User user, String sid) {
		Student student = null;
		if (sid != null && !sid.isEmpty()) {
			student = studentDaoImpl.getStudent(Long.valueOf(sid));
		}
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select * from USER where USER_USERNAME=?");
		query.setString(0, user.getUser_username());
		query.addEntity(User.class);
		List<User> list = query.list();
		if (list == null || list.size() == 0) {
			if (student != null) {
				student.setUser(user);
				session.update(student);
				user.setVerify(true);
			} else {
				user.setVerify(false);
			}
			user.setDenied(false);
			user.setManager(false);
			user.setRegister_date(new Date(System.currentTimeMillis()));
			Serializable save = session.save(user);
			Object object = session.get(User.class, save);
			if (object != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
