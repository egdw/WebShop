package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.ContactLeaveDaoImpl;
import com.web.shop.model.ContactLeave;

@Service
public class ContactLeaveService {
	@Autowired
	private ContactLeaveDaoImpl daoImpl;
	
	@Transactional
	public ArrayList<ContactLeave> getContactLeave(int goodId) {
		ArrayList<ContactLeave> contactLeave = daoImpl.getContactLeave(goodId);
		return contactLeave;
	}
	@Transactional
	public boolean saveContactLeave(ContactLeave contactLeave) {
		return daoImpl.saveContactLeave(contactLeave);
	}
	@Transactional
	public ContactLeave getContactLeaveById(int contactLeaveId) {
		return daoImpl.getContactLeaveById(contactLeaveId);
	}
}
