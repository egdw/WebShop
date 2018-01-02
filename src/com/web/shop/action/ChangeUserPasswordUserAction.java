package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.User;
import com.web.shop.service.UserService;
import com.web.shop.util.UserUtils;

@Controller
@Scope(value = "prototype")
public class ChangeUserPasswordUserAction extends ActionSupport {
	private String oldPassword;
	private String newPassword;
	private String newPassword2;

	@Autowired
	private UserService userService;

	@Override
	public String execute() throws Exception {
		boolean password = userService.updatePassword(oldPassword, newPassword);
		if (password) {
			return SUCCESS;
		}
		return INPUT;
	}

	@Override
	public void validate() {
		if (oldPassword == null || oldPassword.isEmpty() || newPassword == null
				|| newPassword.isEmpty() || newPassword2 == null
				|| newPassword2.isEmpty()) {
			addActionError("不能输入为空");
			return;
		}
		User currentUser = UserUtils.getCurrentUser();
		String password = currentUser.getUser_password();
		if (!password.equals(oldPassword)) {
			addActionError("原密码错误");
			return;
		}
		if (!newPassword.equals(newPassword2)) {
			addActionError("两次密码输入不相同");
			return;
		}
		super.validate();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

}
