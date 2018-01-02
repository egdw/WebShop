package com.web.shop.daoImpl;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.web.shop.dao.GoodDao;
import com.web.shop.model.Good;
import com.web.shop.model.GoodComplete;
import com.web.shop.model.User;
import com.web.shop.service.UserService;
import com.web.shop.util.UserUtils;

@Component
public class GoodDaoImpl implements GoodDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserService userService;

	@Override
	public ArrayList<Good> getUserGoods(int queryCount, int pageCount) {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null) {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session
					.createSQLQuery("select * from GOOD where GOODUSER = ? and ISSALE = 0 limit ?,?");
			createSQLQuery.setString(0, String.valueOf(user.getUser_id()));
			createSQLQuery.setInteger(1, queryCount * pageCount);
			createSQLQuery.setInteger(2, queryCount);
			createSQLQuery.addEntity(Good.class);
			ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();

			return list;
		}
		return null;
	}

	@Override
	public ArrayList<Good> getUserSaledGoods(int queryCount, int pageCount) {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null) {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session
					.createSQLQuery("select * from GOOD where GOODUSER = ? and ISSALE = ? limit ?,?");
			createSQLQuery.setString(0, String.valueOf(user.getUser_id()));
			createSQLQuery.setInteger(1, 1);
			createSQLQuery.setInteger(2, queryCount * pageCount);
			createSQLQuery.setInteger(3, queryCount);
			createSQLQuery.addEntity(Good.class);
			ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();
			return list;
		}
		return null;
	}

	@Override
	public int getUserGoodsSaleNum() {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null) {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session
					.createSQLQuery("select count(*) from GOOD where GOODUSER = ? and ISSALE = 1");
			createSQLQuery.setString(0, String.valueOf(user.getUser_id()));
			int count = ((Number) createSQLQuery.uniqueResult()).intValue();

			return count;
		}
		return 0;
	}

	@Override
	public int getUserGoodsNum() {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null) {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session
					.createSQLQuery("select count(*) from GOOD where GOODUSER = ? and isGet = 0 and ISSALE = 0");
			createSQLQuery.setString(0, String.valueOf(user.getUser_id()));
			int count = ((Number) createSQLQuery.uniqueResult()).intValue();

			return count;
		}
		return 0;
	}

	@Override
	public ArrayList<Good> getLatestGoods(int queryCount, boolean flag) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session
				.createSQLQuery("select * from GOOD where GOODTYPE = ? and isGet = 0 order by CREATEDATE desc limit ?");
		if (flag) {
			createSQLQuery.setInteger(0, 0);
		} else {
			createSQLQuery.setInteger(0, 1);
		}
		createSQLQuery.setInteger(1, queryCount);

		createSQLQuery.addEntity(Good.class);
		ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();

		return list;
	}

	@Override
	public Good getGoodById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Good object = (Good) session.get(Good.class, id);
		if (object != null) {
			return object;
		}

		return null;
	}

	@Override
	public ArrayList<Good> getLatestGoodsByUser(int queryCount, int userId) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session
				.createSQLQuery("select * from GOOD where GOODUSER = ? order by CREATEDATE desc limit ?");
		createSQLQuery.setInteger(0, userId);
		createSQLQuery.setInteger(1, queryCount);

		createSQLQuery.addEntity(Good.class);
		ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();
		return list;
	}

	@Override
	public boolean delGood(int goodId) {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		Session session = sessionFactory.getCurrentSession();
		Good object = (Good) session.get(Good.class, goodId);
		if (user == null || object == null) {
			session.flush();
			return false;
		}
		if (user.isManager()) {
			SQLQuery query = session
					.createSQLQuery("delete from USERLEAVEMESSAGE where GOOD = ?");
			query.setInteger(0, goodId);
			query.executeUpdate();
			session.delete(object);
			session.flush();
			return true;
		} else {
			if (object != null
					&& object.getGoodUser().getUser_id() == user.getUser_id()) {
				SQLQuery query = session
						.createSQLQuery("delete from USERLEAVEMESSAGE where GOOD = ?");
				query.setInteger(0, goodId);
				query.executeUpdate();
				session.delete(object);
				session.flush();
				return true;
			}
		}

		session.flush();
		return false;
	}

	@Override
	public boolean delGoodsByUserId(long userId) {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null && user.isManager()) {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session
					.createSQLQuery("delete from GOOD where GOODUSER = ?");
			query.setLong(0, userId);
			int update = query.executeUpdate();
			session.flush();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Good> getUserGoodsByType(int queryCount, int pageCount,
			int type) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = null;
		if (type != 0) {
			createSQLQuery = session
					.createSQLQuery("select * from GOOD where GOODFROM = ? and isGet = 0 limit ?,?");
			createSQLQuery.setInteger(0, type);
			createSQLQuery.setInteger(1, queryCount * pageCount);
			createSQLQuery.setInteger(2, queryCount);
		} else {
			createSQLQuery = session
					.createSQLQuery("select * from GOOD where isGet = 0 limit ?,?");
			createSQLQuery.setInteger(0, queryCount * pageCount);
			createSQLQuery.setInteger(1, queryCount);
		}
		createSQLQuery.addEntity(Good.class);
		ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();
		return list;
	}

	@Override
	public int getUserFindByNameCount(String regex) {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		if (user != null) {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery createSQLQuery = session
					.createSQLQuery("select count(*) from GOOD where GOODNAME like ? and isGet = 0");
			createSQLQuery.setString(0, "%" + regex + "%");
			int count = ((Number) createSQLQuery.uniqueResult()).intValue();
			return count;
		}
		return 0;
	}

	@Override
	public int getUserFindByTypeCount(int type) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = null;
		if (type == 0) {
			createSQLQuery = session
					.createSQLQuery("select count(*) from GOOD where isGet = 0");
		} else {
			createSQLQuery = session
					.createSQLQuery("select count(*) from GOOD where GOODFROM = ? and isGet = 0");
			createSQLQuery.setInteger(0, type);
		}
		int count = ((Number) createSQLQuery.uniqueResult()).intValue();
		return count;
	}

	@Override
	public ArrayList<Good> getGoodsByFindName(String regex, int queryCount,
			int pageCount) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session
				.createSQLQuery("select * from GOOD where GOODNAME like ? and isGet = 0 limit ?,?");
		createSQLQuery.setString(0, "%" + regex + "%");
		createSQLQuery.setInteger(1, queryCount * pageCount);
		createSQLQuery.setInteger(2, queryCount);
		createSQLQuery.addEntity(Good.class);
		ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();
		return list;
	}

	@Override
	public Good getGood(int goodId) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session
				.createSQLQuery("select * from GOOD where GOODID = ?");
		createSQLQuery.setInteger(0, goodId);
		createSQLQuery.addEntity(Good.class);
		ArrayList<Good> list = (ArrayList<Good>) createSQLQuery.list();
		return list.get(0);
	}

	@Override
	public boolean saleComplete(Integer goodId, Integer userId) {
		if (goodId == null) {
			return false;
		}
		Session session = sessionFactory.getCurrentSession();
		User currentUser = UserUtils.getCurrentUser();
		Good good = (Good) session.get(Good.class, goodId);
		if (good.isGet() || good.isSale()) {
			return false;
		}
		if (good != null
				&& currentUser.getUser_id() == good.getGoodUser().getUser_id()) {
			good.setSale(true);
			good.setGet(true);
			session.update(good);
			session.flush();
			GoodComplete goodComplete = new GoodComplete();
			goodComplete.setGoodId(good);
			goodComplete.setOwnerUser(userService.getUserById(userId));
			session.save(goodComplete);
			return true;
		}

		return false;
	}
}
