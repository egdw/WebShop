package com.web.shop.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.LeaveMessageDaoImpl;

@Service
public class LeaveMessageService {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private LeaveMessageDaoImpl daoImpl;
	
	@Transactional
	public String deleteLeaveMessage(Integer id) {
		String string = daoImpl.delLeaveMessage(id);
		return string;
	}
}
