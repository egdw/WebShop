package com.web.shop.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionContext;

public class SessionFactoryUtils {
	public static SessionFactory getSessionFactory(){
		SessionFactory factory = (SessionFactory) ActionContext.getContext().getApplication().get("sessionFactory");
		return factory;
	}
	
	public static Session getSession(){
		SessionFactory factory = (SessionFactory) ActionContext.getContext().getApplication().get("sessionFactory");
		Session session = factory.openSession();
		return session;
	}
}
