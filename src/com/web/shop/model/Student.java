package com.web.shop.model;

public class Student {
	private Long sid;
	private String studentName;
	private User user;
	
	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", studentName=" + studentName + "]";
	}

	public Student(Long sid, String studentName) {
		super();
		this.sid = sid;
		this.studentName = studentName;
	}

	public Student() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
