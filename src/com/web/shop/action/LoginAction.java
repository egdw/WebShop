package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.User;
import com.web.shop.service.LoginService;
import com.web.shop.util.UserUtils;

@Controller
@Scope(value = "prototype")
public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoginService loginService;
	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		User user = new User(username, password);
		boolean flag = loginService.loginAccount(user);
		if (flag) {
			User user2 = loginService.getUserByUsernameAndPassword(username,
					password);
			if (user2 != null) {
				// 保存数据道session对象中
				if (user2.isDenied()) {
					addActionError("您已经被禁止登陆到服务器");
					return INPUT;
				}
				if (!user2.isVerify()) {
					addActionError("您是未验证用户请等待管理员核实");
					return INPUT;
				}
				ActionContext.getContext().getSession()
						.put("currentUser", user2);
				return SUCCESS;
			} else {
				addActionError("保存用户到session对象中失败");
				return INPUT;
			}
		} else {
			addActionError("密码账号错误！请重试！");
			return INPUT;
		}
	}

	@Override
	public void validate() {

		super.validate();
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

}
