package com.web.shop.util;

import com.opensymphony.xwork2.ActionContext;
import com.web.shop.model.User;

public class UserUtils {

	public static User getCurrentUser() {
		User user = (User) ActionContext.getContext().getSession()
				.get("currentUser");
		return user;
	}
}
