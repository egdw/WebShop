package com.web.shop.daoImpl;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.BannerDao;
import com.web.shop.model.Banner;

@Component
public class BannerDaoImpl implements BannerDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<Banner> getBanners() {
		Session currentSession = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = currentSession
				.createSQLQuery("select * from BANNER");
		sqlQuery.addEntity(Banner.class);
		ArrayList<Banner> list = (ArrayList<Banner>) sqlQuery.list();
		return list;
	}

	@Override
	public boolean delBanner(int bannerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Banner banner = (Banner) currentSession.get(Banner.class, bannerId);
		if (banner == null) {
			return false;
		}
		currentSession.delete(banner);
		return true;
	}

}
