package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.UserManagerService;

public class UserManagerAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private int userId;
	@Autowired
	private UserManagerService managerService;

	@Override
	public String execute() throws Exception {
		if (type != null) {
			switch (type) {
			case "delUser":
				boolean delUser = managerService.delUser(userId);
				if (delUser) {
					return SUCCESS;
				} else {
					addActionError("删除用户失败");
					return INPUT;
				}
			case "setVerify":
				boolean verify = managerService.setVerify(userId);
				if (verify) {
					return SUCCESS;
				} else {
					addActionError("验证通过失败");
					return INPUT;
				}

			case "setManager":
				boolean b = managerService.setManager(userId);
				if (b) {
					return SUCCESS;
				} else {
					addActionError("设置管理员失败");
					return INPUT;
				}

			case "setDined":
				boolean setDined = managerService.setDined(userId);
				if (setDined) {
					return SUCCESS;
				} else {
					addActionError("封禁用户失败");
					return INPUT;
				}
			default:
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	

}
