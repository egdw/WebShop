package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.UserManagerService;

@Controller
@Scope(value = "prototype")
public class DelUserAction extends ActionSupport {
	@Autowired
	private UserManagerService userManagerService;

	private int userId;

	@Override
	public String execute() throws Exception {
		boolean delUser = userManagerService.delUser(getUserId());
		if(delUser){
			return SUCCESS;
		}
		return ERROR;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
