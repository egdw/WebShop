package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.ContactLeave;

@Repository
public interface ContactLeaveDao {
	public ArrayList<ContactLeave> getContactLeave(int goodId);

	public boolean saveContactLeave(ContactLeave contactLeave);

	public ContactLeave getContactLeaveById(int contactLeaveId);
}
