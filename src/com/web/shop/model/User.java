package com.web.shop.model;

import java.sql.Date;

public class User {
	private Integer user_id;
	private String user_username;
	private String user_password;
	private Date register_date;
	private boolean denied;
	private boolean manager;
	private boolean verify;
	private String headImage;
	private String email;
	private boolean isEmailVerify;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public User(String user_username, String user_password) {
		super();
		this.user_username = user_username;
		this.user_password = user_password;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public boolean isDenied() {
		return denied;
	}

	public void setDenied(boolean denied) {
		this.denied = denied;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public User() {
		super();
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailVerify() {
		return isEmailVerify;
	}

	public void setEmailVerify(boolean isEmailVerify) {
		this.isEmailVerify = isEmailVerify;
	}

	
}
