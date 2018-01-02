package com.web.shop.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.LeaveMessageDao;
import com.web.shop.model.UserLeaveMessage;

@Component
public class LeaveMessageDaoImpl implements LeaveMessageDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String delLeaveMessage(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		UserLeaveMessage object = (UserLeaveMessage) session.get(
				UserLeaveMessage.class, id);
		if (object != null) {
			session.delete(object);
			return "success";
		} else {
			return "input";
		}
	}

}
