package com.web.shop.listener;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.web.shop.model.Banner;
import com.web.shop.model.User;
import com.web.shop.util.RandomUtils;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@SuppressWarnings("resource")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		SessionFactory bean = context.getBean(SessionFactory.class);
		Session session = bean.openSession();
		SQLQuery query = session
				.createSQLQuery("select * from USER where USER_USERNAME = ?");
		query.setString(0, "admin");
		query.addEntity(User.class);
		List list = query.list();
		if (list.size() == 0) {
			User user = new User();
			user.setRegister_date(new Date(System.currentTimeMillis()));
			user.setManager(true);
			user.setVerify(true);
			user.setUser_password("12345678");
			user.setUser_username("admin");
			session.saveOrUpdate(user);
		}
		SQLQuery bannerSession = session.createSQLQuery("select * from BANNER");
		bannerSession.addEntity(Banner.class);
		List bannerList = bannerSession.list();
		if (bannerList.size() == 0) {
			Banner banner = new Banner();
			banner.setBannerImage(File.separator + "WebShop" + File.separator
					+ "img" + File.separator + "banner_1.jpg");
			Banner banner2 = new Banner();
			banner2.setBannerImage(File.separator + "WebShop" + File.separator
					+ "img" + File.separator + "banner_2.jpg");
			Banner banner3 = new Banner();
			banner3.setBannerImage(File.separator + "WebShop" + File.separator
					+ "img" + File.separator + "banner_3.jpg");
			Banner banner4 = new Banner();
			banner4.setBannerImage(File.separator + "WebShop" + File.separator
					+ "img" + File.separator + "banner_4.jpg");
			Banner banner5 = new Banner();
			banner5.setBannerImage(File.separator + "WebShop" + File.separator
					+ "img" + File.separator + "banner_5.jpg");
			session.save(banner);
			session.save(banner2);
			session.save(banner3);
			session.save(banner4);
			session.save(banner5);
		}
		// String BannerImagepath = ServletActionContext.getServletContext()
		// .getRealPath(File.separatorChar + "BannerImage");
		// String Imagepath = ServletActionContext.getServletContext()
		// .getRealPath(File.separatorChar + "img");
		// for (int i = 0; i < 5; i++) {
		// File file = new File(Imagepath, "banner_" + (i + 1) + ".jpg");
		// try {
		// String code = RandomUtils.getCode();
		// File file2 = new File(BannerImagepath, code + ".jpg");
		// FileUtils.copyFile(file, file2);
		// Banner banner = new Banner();
		// String absolutePath = file2.getAbsolutePath();
		// int indexOf = absolutePath.indexOf(File.separatorChar
		// + "WebShop" + File.separatorChar + "BannerImage"
		// + File.separatorChar);
		// String substring = absolutePath.substring(indexOf);
		// System.out.println(substring);
		// banner.setBannerImage(substring);
		// session.save(banner);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// }
		//
		// }
		session.flush();
		session.close();
	}
}
