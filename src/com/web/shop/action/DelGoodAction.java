package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.GoodService;

@Controller
@Scope(value = "prototype")
public class DelGoodAction extends ActionSupport {
	private int good_id;
	@Autowired
	private GoodService service;
	
	@Override
	public String execute() throws Exception {
		boolean delGood = service.delGood(good_id);
		if (delGood) {
			return SUCCESS;
		} else {
			return ERROR;
		}

	}

	public int getGood_id() {
		return good_id;
	}

	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}

}
