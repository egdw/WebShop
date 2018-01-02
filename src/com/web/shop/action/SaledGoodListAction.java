package com.web.shop.action;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Good;
import com.web.shop.service.GoodService;

@Controller
@Scope(value = "prototype")
public class SaledGoodListAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodService service;
	private int pageCount;
	private final int QUERYCOUNT = 8;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().get("request");
		int userNum = service.getUserGoodsSaleNum();
		double temp = userNum / QUERYCOUNT;
		int pageNum = 1;
		if (temp % 1 != 0) {
			pageNum = (int) (temp + 1);
		} else {
			pageNum = (int) temp;
		}
		request.put("pageNum", pageNum);
		ArrayList<Good> userSaledGoods = service.getUserSaledGoods(QUERYCOUNT,
				pageCount);
		request.put("goods", userSaledGoods);
		return SUCCESS;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
