package com.web.shop.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getSession().clear();
		return "noLogin";
	}
}
