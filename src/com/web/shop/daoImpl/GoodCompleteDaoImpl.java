package com.web.shop.daoImpl;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.GoodCompleteDao;
import com.web.shop.model.GoodComplete;
import com.web.shop.util.UserUtils;

@Component
public class GoodCompleteDaoImpl implements GoodCompleteDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<GoodComplete> getCompleteGoods(int queryCount, int pageCount) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session
				.createSQLQuery("select * from GOODCOMPLETE where OWNERUSER = ? limit ?,?");
		createSQLQuery.setString(0,
				String.valueOf(UserUtils.getCurrentUser().getUser_id()));
		createSQLQuery.setInteger(1, queryCount * pageCount);
		createSQLQuery.setInteger(2, queryCount);
		createSQLQuery.addEntity(GoodComplete.class);
		ArrayList<GoodComplete> list = (ArrayList<GoodComplete>) createSQLQuery.list();
		return list;
	}

	@Override
	public int getCompleteGoodsNum() {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session
				.createSQLQuery("SELECT count(*) FROM GOODCOMPLETE where OWNERUSER = ?");
		createSQLQuery.setString(0,
				String.valueOf(UserUtils.getCurrentUser().getUser_id()));
		int count = ((Number) createSQLQuery.uniqueResult()).intValue();
		return count;
	}

}
