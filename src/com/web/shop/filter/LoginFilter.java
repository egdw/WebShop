package com.web.shop.filter;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.web.shop.model.User;

public class LoginFilter extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext context = arg0.getInvocationContext();
		Map<String, Object> session = context.getSession();
		User object = (User) session.get("currentUser");
		if (object == null) {
			return "noLogin";
		}
		return arg0.invoke();
	}

}
