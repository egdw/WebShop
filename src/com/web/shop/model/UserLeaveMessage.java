package com.web.shop.model;

import java.sql.Date;

public class UserLeaveMessage {
	private Integer userLeaveMessageId;
	private Good good;
	private Date createDate;
	private String context;
	private User user;

	public UserLeaveMessage(Integer userLeaveMessageId, Good good, Date createDate, String context, User user) {
		super();
		this.userLeaveMessageId = userLeaveMessageId;
		this.good = good;
		this.createDate = createDate;
		this.context = context;
		this.user = user;
	}

	public UserLeaveMessage() {
		super();
	}

	@Override
	public String toString() {
		return "UserLeaveMessage [userLeaveMessageId=" + userLeaveMessageId + ", good=" + good + ", createDate="
				+ createDate + ", context=" + context + ", user=" + user + "]";
	}

	public Integer getUserLeaveMessageId() {
		return userLeaveMessageId;
	}

	public void setUserLeaveMessageId(Integer userLeaveMessageId) {
		this.userLeaveMessageId = userLeaveMessageId;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
