package com.web.shop.filter;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.web.shop.model.User;

public class AdminFilter extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext invocationContext = arg0.getInvocationContext();
		Map<String, Object> session = invocationContext.getSession();
		User user = (User) session.get("currentUser");
		if (user != null && user.isManager()) {
			return arg0.invoke();
		}
		return "error";
	}

}
