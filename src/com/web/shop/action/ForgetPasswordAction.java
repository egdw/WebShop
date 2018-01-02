package com.web.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope(value = "prototype")
public class ForgetPasswordAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		//get type
		//get email
		//get randomCode
		//changePassword;
		return super.execute();
	}
}
