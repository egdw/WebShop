package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.GoodService;

@Controller
@Scope(value = "prototype")
public class GoodCompleteAction extends ActionSupport {
	private Integer goodId;
	private Integer userId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodService goodService;

	@Override
	public String execute() throws Exception {
		boolean b = goodService.saleComplete(goodId, userId);
		if (b) {
			return SUCCESS;
		} else {
			addActionError("完成商品交易失败");
			return ERROR;
		}

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

}
