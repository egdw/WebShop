package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Student;
import com.web.shop.model.User;
import com.web.shop.service.RegisterService;
import com.web.shop.service.StudentService;

@Controller
@Scope(value = "prototype")
public class RegisterAction extends ActionSupport {
	@Autowired
	private RegisterService service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String confirm_password;
	private String sid;
	@Autowired
	private StudentService studentService;

	@Override
	public String execute() throws Exception {
		User user = new User(username, password);
		boolean register = service.register(user, sid);
		if (register) {
			return SUCCESS;
		}
		addActionError("注册发生异常错误！");
		return INPUT;
	}

	@Override
	public void validate() {
		if (username == null || password == null || confirm_password == null) {
			addActionError("注册发生异常错误！");
			return;
		}
		if (username.length() > 10) {
			addActionError("用户名长度太短");
			return;
		}

		if (!password.equals(confirm_password)) {
			addActionError("两次密码输入不相同");
			return;
		}

		if (password.length() <= 6) {
			addActionError("密码长度太短。请重新输入");
			return;
		}

		if (password.length() > 17) {
			addActionError("密码长度不能超过17位。请重新输入");
			return;
		}
		if (sid != null && !sid.isEmpty()) {
			Student student = studentService.getStudent(Long.valueOf(sid));
			if (student != null) {
				User user = student.getUser();
				if(user!=null){
					addActionError("该学号已经被人注册！");
				}
			}else{
				addActionError("该学号不存在！");
			}
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
