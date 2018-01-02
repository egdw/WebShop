package com.web.shop.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.ContactLeaveDao;
import com.web.shop.model.ContactLeave;
import com.web.shop.model.Good;
import com.web.shop.util.UserUtils;

@Component
public class ContactLeaveDaoImpl implements ContactLeaveDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<ContactLeave> getContactLeave(int goodId) {
		Session currentSession = sessionFactory.getCurrentSession();
		SQLQuery query = currentSession
				.createSQLQuery("select * from CONTACTLEAVE where GOOD = ?");
		query.setInteger(0, goodId);
		query.addEntity(ContactLeave.class);
		ArrayList<ContactLeave> list = (ArrayList<ContactLeave>) query.list();
		return list;
	}

	@Override
	public boolean saveContactLeave(ContactLeave contactLeave) {
		Session currentSession = sessionFactory.getCurrentSession();
		if (contactLeave.getSendUser().getUser_id() == contactLeave.getGood()
				.getGoodUser().getUser_id()) {
			// 判断是否为卖家自身，如果是自身返回false
			return false;
		}
		currentSession.saveOrUpdate(contactLeave);
		currentSession.flush();
		return true;
	}

	@Override
	public ContactLeave getContactLeaveById(int contactLeaveId) {
		Session currentSession = sessionFactory.getCurrentSession();
		ContactLeave object = (ContactLeave) currentSession.get(
				ContactLeave.class, contactLeaveId);
		return object;
	}

}
