package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.ContactLeave;
import com.web.shop.service.ContactLeaveService;
import com.web.shop.service.GoodService;
import com.web.shop.service.UserService;

@Controller
@Scope(value = "prototype")
public class CommitContactLeaveAction extends ActionSupport {
	private Integer goodId;
	private Integer userId;
	private String message;
	private String mobilePhone;
	@Autowired
	private GoodService goodService;
	@Autowired
	private UserService userService;
	@Autowired
	private ContactLeaveService contactLeaveService;

	@Override
	public String execute() throws Exception {
		ContactLeave contactLeave = new ContactLeave();
		contactLeave.setGood(goodService.getGoodById(goodId));
		contactLeave.setMessage(message);
		contactLeave.setMobilePhone(mobilePhone);
		contactLeave.setSendUser(userService.getUserById(userId));
		boolean leave = contactLeaveService.saveContactLeave(contactLeave);
		if(leave){
			return SUCCESS;
		}
		return ERROR;
	}

	@Override
	public void validate() {
		super.validate();
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
