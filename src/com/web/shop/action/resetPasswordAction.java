package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.UserManagerService;

@Controller
@Scope(value = "prototype")
public class resetPasswordAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManagerService service;
	private int userId;

	@Override
	public String execute() throws Exception {
		String password = service.resetUserPassword(userId);
		if (password != null) {
			ActionContext.getContext().getSession().put("newPasswd", "新密码：" + password);
			return INPUT;
		}
		addActionMessage("重置密码失败");
		return INPUT;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
