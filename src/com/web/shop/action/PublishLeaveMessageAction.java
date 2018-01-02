package com.web.shop.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.UserService;

@Controller
@Scope(value = "prototype")
public class PublishLeaveMessageAction extends ActionSupport {
	private int goodId;
	private String context;
	@Autowired
	private UserService service;

	@Override
	public String execute() throws Exception {
		boolean b = service.savePublishLeaveMessage(goodId, context);
		if(b){
			((Map<String, Object>) ActionContext.getContext().get("request")).put("goodId", goodId);
			return SUCCESS;
		}
		return ERROR;
	}

	@Override
	public void validate() {
		if (context == null || context.isEmpty()) {
			addActionError("内容不能为空！");
		}
		super.validate();
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
}
